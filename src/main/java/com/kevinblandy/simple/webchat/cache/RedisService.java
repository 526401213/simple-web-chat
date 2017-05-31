package com.kevinblandy.simple.webchat.cache;

import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kevinblandy.simple.webchat.utils.GeneralUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月23日 下午3:44:44
 */
@Component
public class RedisService {
	
	@Autowired
	private JedisPool jedisPool;
	
	@Value(value = "${jedis.auth: }")
	private String auth;
	
	/**
	 * execute
	 * @param fun
	 * @return
	 */
	private <T> T execute(Function<Jedis,T> fun){
		try(Jedis jedis = this.jedisPool.getResource()){
			if(!GeneralUtils.isEmpty(this.auth)){
				jedis.auth(auth);
			}
			return fun.apply(jedis);
		}
	}
	
	/**
     * 添加一个值
     * @param key
     * @param value
     * @return
     */
    public String set(String key,String value){
        return this.execute(jedis -> {
           return jedis.set(key,value);
        });
    }

    /**
     * 添加值,并且设置过期时间
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public Long set(String key,String value,int seconds){
        return this.execute(jedis -> {
            this.set(key,value);
            return this.expire(key,seconds);
        });
    }

    /**
     * 设置指定key的生命周期
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key,int seconds){
        return this.execute(jedis -> {
           return jedis.expire(key,seconds);
        });
    }

    /**
     * 根据key检索记录
     * @param key
     * @return
     */
    public String get(String key){
        return this.execute(jedis -> {
            return jedis.get(key);
        });
    }

    /**
     * 根据ID删除key
     * @param key
     * @return
     */
    public Long del(String key){
        return this.execute(jedis -> {
            return jedis.del(key);
        });
    }

    /**
     * 判断指定的key是否存在
     * @param key
     * @return
     */
    public Boolean exits(String key){
        return this.execute(jedis -> {
           return jedis.exists(key);
        });
    }

    /**
     * 根据表达式检索记录
     * @param key
     * @return
     */
    public Set<String> keys(String key){
        return this.execute(jedis -> {
           return jedis.keys(key);
        });
    }
    
    /**
     * 添加数据到集合
     * @param key
     * @param values
     * @return
     */
    public Long sadd(String key,String... values){
    	return this.execute(jedis -> {
    		return jedis.sadd(key, values);
    	});
    }
    
    /**
     * 获取集合的所有数据
     * @param key
     * @return
     */
    public Set<String> smembers(String key){
    	return this.execute(jedis -> {
    		return jedis.smembers(key);
    	});
    }
    
    /**
     * 从集合中移除数据
     * @param key
     * @param value
     * @return
     */
    public Long srem(String key,String... value){   
    	return this.execute(jedis -> {
    		return jedis.srem(key, value);
    	});
    }
    
    /**
     * 判断数据是否存在集合中
     * @param key
     * @param value
     * @return
     */
    public Boolean sismember(String key,String value){
    	return this.execute(jedis -> {
    		return jedis.sismember(key,value);
    	});
    }
    
    /**
     * 获取集合中的数据长度
     * @param key
     * @return
     */
    public Long scard(String key){
    	return this.execute(jedis -> {
    		return jedis.scard(key);
    	});
    }
}
