package com.kevinblandy.simple.webchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Repository;

/**
 * Created by KevinBlandy on 2017/5/19 18:03
 * 
 * 殆尽青颜空辞岁
 * 暮暮回首几朝夕
 * 
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.kevinblandy"},annotationClass = Repository.class)
@ServletComponentScan(basePackages = {"com.kevinblandy"})
public class SimpleWebChatApplication{

    public static void main(String[] args){
        SpringApplication.run(SimpleWebChatApplication.class,args);
    }
    
}
