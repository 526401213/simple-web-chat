package com.kevinblandy.simple.webchat.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonpHttpMessageConverter4;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;

/**
 * Created by KevinBlandy on 2017/5/23 16:47
 */
@Configuration
public class HttpMessageConverterConfiguration {
	/**
	 * FastJsonpHttpMessageConverter4
	 * @return
	 */
	@Bean
	public HttpMessageConverters httpMessageConverter(){
		FastJsonpHttpMessageConverter4 fastJsonpHttpMessageConverter4 = new FastJsonpHttpMessageConverter4();
		fastJsonpHttpMessageConverter4.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setCharset(StandardCharsets.UTF_8);
		fastJsonConfig.setSerializerFeatures(
				SerializerFeature.PrettyFormat,				//格式化
				//SerializerFeature.WriteMapNullValue,		//输出null字段
				SerializerFeature.QuoteFieldNames,			//使用双引号
				SerializerFeature.WriteNullListAsEmpty);	//把null集合/数组输出为[]
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonpHttpMessageConverter4.setFastJsonConfig(fastJsonConfig);
		return new HttpMessageConverters(fastJsonpHttpMessageConverter4);
	}
	
	/**
	 * 跨域支持
	 * @return
	 */
	@Bean
	public FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice(){
		FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice = new FastJsonpResponseBodyAdvice("callback","jsonp");
		return fastJsonpResponseBodyAdvice;
	}
}