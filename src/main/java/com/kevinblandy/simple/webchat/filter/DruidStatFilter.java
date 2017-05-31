package com.kevinblandy.simple.webchat.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by KevinBlandy on 2017/5/19 18:59
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
        initParams={@WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")})
public class DruidStatFilter extends WebStatFilter {

}
