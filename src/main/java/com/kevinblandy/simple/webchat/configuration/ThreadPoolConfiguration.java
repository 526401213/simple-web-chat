package com.kevinblandy.simple.webchat.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
 * 线程池
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月24日 下午4:13:46
 */
@Configuration
public class ThreadPoolConfiguration {
	
	@Bean
	@ConfigurationProperties(prefix = "spring.threadpool")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		return threadPoolTaskExecutor;
	}
}
