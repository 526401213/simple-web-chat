package com.kevinblandy.simple.webchat.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * RedisConfiguration
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月23日 下午3:28:51
 */
@Configuration
public class RedisConfiguration {
	
	@Value(value = "${jedis.ip}")
	private String ip;
	
	@Value(value = "${jedis.port}")
	private Integer port;
	
	@Bean
	@ConfigurationProperties(prefix = "jedis.config")
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		//jedisPoolConfig.setMaxTotal();
		return jedisPoolConfig;
	}
	
	@Bean
	public JedisPool jedisPool(){
		JedisPoolConfig jedisPoolConfig = jedisPoolConfig();
		JedisPool jedisPool = new JedisPool(jedisPoolConfig,ip,port);
		return jedisPool;
	}
}
