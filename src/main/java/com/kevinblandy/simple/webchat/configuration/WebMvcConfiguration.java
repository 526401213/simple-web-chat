package com.kevinblandy.simple.webchat.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kevinblandy.simple.webchat.interceptor.UserInterceptor;
/**
 * 
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月23日 下午6:04:58
 */
@Configuration
public class WebMvcConfiguration extends  WebMvcConfigurerAdapter{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/login");
		super.addInterceptors(registry);
	}
	
	@Bean
	public UserInterceptor userInterceptor() {
		return new UserInterceptor();
	}
}
