package com.kevinblandy.simple.webchat.views;

import org.springframework.web.servlet.ModelAndView;

public interface StandardViews {
	/**
	 * Index
	 */
	ModelAndView INDEX = new ModelAndView("index/index");
	
	/**
	 * login
	 */
	ModelAndView LOGIN = new ModelAndView("login/login");
}
