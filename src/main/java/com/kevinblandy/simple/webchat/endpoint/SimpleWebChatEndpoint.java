package com.kevinblandy.simple.webchat.endpoint;

import com.kevinblandy.simple.webchat.cache.RedisService;
import com.kevinblandy.simple.webchat.cache.UserCacheProvider;
import com.kevinblandy.simple.webchat.code.RedisKey;
import com.kevinblandy.simple.webchat.configurator.SimpleWebChatConfigurator;
import com.kevinblandy.simple.webchat.decoder.MessageDecoder;
import com.kevinblandy.simple.webchat.encoder.MessageEncoder;
import com.kevinblandy.simple.webchat.entity.RecordEntity;
import com.kevinblandy.simple.webchat.entity.UserEntity;
import com.kevinblandy.simple.webchat.enums.MessageType;
import com.kevinblandy.simple.webchat.enums.State;
import com.kevinblandy.simple.webchat.model.ChatGroupMessage;
import com.kevinblandy.simple.webchat.model.ChatMessage;
import com.kevinblandy.simple.webchat.model.ChatOnlineUsersMessage;
import com.kevinblandy.simple.webchat.model.ChatSingleUserEventMessage;
import com.kevinblandy.simple.webchat.service.RecordService;
import com.kevinblandy.simple.webchat.utils.GeneralUtils;
import com.kevinblandy.simple.webchat.utils.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by KevinBlandy on 2017/5/21 16:07
 */
@Component
@ServerEndpoint(value = "/chat/{userId}",
	decoders = {MessageDecoder.class},
	encoders = {MessageEncoder.class},
	configurator = SimpleWebChatConfigurator.class)
public class SimpleWebChatEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleWebChatEndpoint.class);

	private static RedisService redisService;

	private static UserCacheProvider userCacheProvider;
	
	private static RecordService recordService;
	
	private static ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	private static final ConcurrentHashMap<String, Session> ONLINES = new ConcurrentHashMap<>();

	private Session session;	
	
	private String userId;
	
	static {
		
	}

	public SimpleWebChatEndpoint(){
		
	}
	
	/**
	 * 新的消息
	 * @param message
	 * @throws IOException
	 * @throws EncodeException
	 */
    @OnMessage(maxMessageSize = (1024 * 1024 * 50))		//MaxSize = 50MB
    public void onMessage(ChatMessage message) throws IOException, EncodeException{
    	if(message.getSuccess()) {
    		switch (message.getType()) {
				case GROUP:{		//群组消息
						ChatGroupMessage chatGroupMessage = (ChatGroupMessage) message;
						LOGGER.info("新的群组消息 ==> userId={},sessionId={},content={}",userId,session.getId(),chatGroupMessage.getContent());
						radio(chatGroupMessage, (session) -> {
			        		//广播消息
			        		return !session.equals(this.session);
			        	});
						//保存记录 
						getThreadPoolTaskExecutor().execute(() -> {
							RecordEntity recordEntity = new RecordEntity();
							recordEntity.setRecordId(GeneralUtils.getUUID());
							recordEntity.setCreateDate(new Date());
							recordEntity.setContent(chatGroupMessage.getContent());
							recordEntity.setState(State.ENABLED);
							recordEntity.setUserId(chatGroupMessage.getFrom().getUserId());
							try {
								getRecordService().create(recordEntity);
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
						break;
					}
				case CHAT_APPLY:{			//P2P消息申请
					//TODO
					break;
				}
				case CHAT_AGREE:{			//P2P消息同意
					//TODO			
					break;
				}
				case CHAT_CLOSE:{			//P2P消息关闭
					//TODO
					break;	
				}
				default:{					//非法信息
					//TODO
					break;
				}
			}
    	}
    }
    
    /**
     * 新的连接
     * @param userId
     * @param session
     * @param endpointConfig
     * @throws IOException
     * @throws EncodeException
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String userId,Session session, EndpointConfig endpointConfig) throws IOException, EncodeException {
    	LOGGER.info("新的连接 ==> userId={},sessionId={}",userId,session.getId());
    	UserEntity userEntity = null;
		try {
			userEntity = getUserCacheProvider().loadUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
			//非法用户,不鸟
			session.close();
			return;
		}
		//尝试根据用户ID检索连接ID
    	String existsSessionId = getRedisService().get(userId);
    	 //存在连接ID,当前用户已经登录
    	if(!GeneralUtils.isEmpty(existsSessionId)){
    		//尝试从在线连接中获取已登录连接
            Session existsSession = ONLINES.get(existsSessionId);
            //确定该连接存在,且是开启状态
            if(existsSession != null && existsSession.isOpen()){
                //向该连接,提示异地登录信息			
                existsSession.getBasicRemote().sendObject(new ChatSingleUserEventMessage(null,MessageType.OTHER_LOGIN));		
                //关闭之
                existsSession.close();
                //从连接组移除
                ONLINES.remove(existsSessionId);
            }
        }
    	//存入用户ID 与会话ID的映射关系
    	getRedisService().set(userId, session.getId());
    	//广播加入信息
    	radio(new ChatSingleUserEventMessage(userEntity, MessageType.JOIN), (item)->{	
    		return true;
    	});
    	//存入会话
    	ONLINES.put(session.getId(), session);
        this.userId = userId;
        this.session = session;
        //检索所有用户信息
        List<UserEntity> users = getRedisService().smembers(RedisKey.ONLINE_USERS).parallelStream().map(item -> {
    		try {
				return getUserCacheProvider().loadUser(item);
			} catch (Exception e) {
				return null;
			}
    	}).filter(item -> {
    		return item != null;
    	}).collect(Collectors.toList());
        //向当前连接,响应在线用户信息
        this.session.getBasicRemote().sendObject(new ChatOnlineUsersMessage(users));
        //当前连接存入在线信息
        getRedisService().sadd(RedisKey.ONLINE_USERS,userId);
    }
    
    /**
     * 退出群聊
     * @param closeReason
     * @throws Exception
     */
    @OnClose
    public void onClose(CloseReason closeReason) throws Exception{
    	LOGGER.info("退出群聊 userId={},sessionId={}",this.userId,this.session.getId());
    	//移除 用户ID 与会话ID的映射关系
    	getRedisService().del(userId);
    	//移除会话
    	ONLINES.remove(this.session.getId());
    	//删除在线信息
    	getRedisService().srem(RedisKey.ONLINE_USERS,userId);
    	//检索出退出登录的用户信息
    	UserEntity userEntity = getUserCacheProvider().loadUser(this.userId);
    	//广播退出消息		
    	radio(new ChatSingleUserEventMessage(userEntity, MessageType.EXIT), (session) -> {		
    	    return Boolean.TRUE;
    	});
    }

    @OnError
    public void onError(Throwable throwable){
    	//TODO 
    }
    
    /**
     * 消息广播
     * @param message
     * @param predicate
     * @throws IOException
     * @throws EncodeException
     */
    public static void radio(ChatMessage message,Predicate<Session> predicate) throws IOException, EncodeException{
        ONLINES.values().parallelStream().filter(predicate).forEach(session -> {
            if(session.isOpen()){
                try {
                    session.getBasicRemote().sendObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Service...
	private static RedisService getRedisService() {
		if(redisService == null) {
			  redisService = SpringContext.getBean(RedisService.class);
		}
		return redisService;
	}

	private static UserCacheProvider getUserCacheProvider() {
		if(userCacheProvider == null) {
			userCacheProvider = SpringContext.getBean(UserCacheProvider.class);
		}
		return userCacheProvider;
	}

	private static RecordService getRecordService() {
		if(recordService == null) {
			recordService = SpringContext.getBean(RecordService.class);
		}
		return recordService;
	}

	private static ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
		if(threadPoolTaskExecutor == null) {
			threadPoolTaskExecutor = SpringContext.getBean(ThreadPoolTaskExecutor.class);
		}
		return threadPoolTaskExecutor;
	}
}
