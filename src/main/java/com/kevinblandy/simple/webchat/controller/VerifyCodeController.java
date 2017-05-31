package com.kevinblandy.simple.webchat.controller;

import com.kevinblandy.simple.webchat.annotation.NoLogin;
import com.kevinblandy.simple.webchat.code.SessionCode;
import com.kevinblandy.simple.webchat.utils.VerifyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * 验证码Controller
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月12日 上午11:23:25
 */
@Controller
@RequestMapping(value = "verifycode")
public class VerifyCodeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(VerifyCodeController.class);
		
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @param width
	 * @param height
	 * @throws Exception
	 */
	@GetMapping
	@NoLogin
	public void getVerifyCode(HttpServletRequest request,
	                          HttpServletResponse response,
	                          @RequestParam(value = "width" ,defaultValue = "70") Integer width,
	                          @RequestParam(value = "heigth" ,defaultValue = "35") Integer height)throws Exception{
		VerifyCode verifyCode = new VerifyCode(width,height);
		BufferedImage image = verifyCode.getImage();
		String text = verifyCode.getText();
		LOG.debug("验证码 = {}",text);
		request.getSession().setAttribute(SessionCode.VERIFY_CODE,text);
		ImageIO.write(image,"png",response.getOutputStream());
	}
	
}
