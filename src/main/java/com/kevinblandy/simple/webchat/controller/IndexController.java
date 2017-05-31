package com.kevinblandy.simple.webchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kevinblandy.simple.webchat.views.StandardViews;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by KevinBlandy on 2017/5/19 18:20
 */
@Controller
@RequestMapping
public class IndexController {
//
//	@Autowired
//	private RedisService redisService;
//	
//	@Autowired
//	private UserCacheProvider userCacheProvider;
	
    /**
     * 主页
     * @param request
     * @return
     */
    @GetMapping(value = "index")
    public ModelAndView index(HttpServletRequest request){
//    	ModelAndView index = new ModelAndView("index/index");
//    	List<UserEntity> users = this.redisService.smembers(RedisKey.ONLINE_USERS).parallelStream().map(item -> {
//    		try {
//				return this.userCacheProvider.loadUser(item);
//			} catch (Exception e) {
//				return null;
//			}
//    	}).filter(item -> {
//    		return item != null;
//    	}).collect(Collectors.toList());
//    	index.addObject("users", users);		
        return StandardViews.INDEX;
    }
    
    @GetMapping
    public String index(){
        return "forward:index";
    }
}
