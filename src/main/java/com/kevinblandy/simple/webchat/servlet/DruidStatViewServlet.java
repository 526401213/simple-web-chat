package com.kevinblandy.simple.webchat.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Created by KevinBlandy on 2017/5/19 18:59
 */
@WebServlet(urlPatterns = "/druid/*",
        initParams={@WebInitParam(name="loginUsername",value="KevinBlandy"),
                @WebInitParam(name="loginPassword",value="778899")})
public class DruidStatViewServlet extends StatViewServlet {

    private static final long serialVersionUID = -1625661402500921518L;
}