package com.kevinblandy.simple.webchat.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kevinblandy.simple.webchat.utils.SpringContext;
/**
 * 
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月23日 下午3:17:50
 */
@Configuration
public class SpringContextConfiguration {
	
	@Bean
	public SpringContext context(){
		return new SpringContext();
	}
}
