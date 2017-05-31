package com.kevinblandy.simple.webchat.configuration;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipartConfiguration {
	
	@Value(value = "${file.upload.maxFileSize}")
	private String maxFileSize;
	
	@Value(value = "${file.upload.maxRequestSize}")
	private String maxRequestSize;
	
	@Bean  
	public MultipartConfigElement multipartConfigElement() {  
		MultipartConfigFactory factory = new MultipartConfigFactory();  
		factory.setMaxFileSize(maxFileSize);  
		factory.setMaxRequestSize(maxRequestSize);
		return factory.createMultipartConfig();  
	}  
}
