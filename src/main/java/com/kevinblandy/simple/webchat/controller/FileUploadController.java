package com.kevinblandy.simple.webchat.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kevinblandy.simple.webchat.utils.DateUtils;
import com.kevinblandy.simple.webchat.utils.GeneralUtils;

/**
 * 图片上传
 * @author	KevinBlandy
 * @version	1.0
 * @date	2017年5月23日 上午11:07:39
 */
@Controller
@RequestMapping(value = "upload")
public class FileUploadController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
	
	private static final String ERROR_MESSAGE = "error|服务器端错误";
	
	@Value(value = "${file.upload.local}")
	private String local;
	
	@Value(value = "${file.upload.prefix}")
	private String prefix;
	
	private AtomicInteger atomicInteger = new AtomicInteger(0); 
	
	/**
	 * 编辑器图片上传
	 * @param multipartFile
	 * @return
	 * @throws IOException 
	 */
	@PostMapping
	//@ResponseBody 
	public void upload(@RequestParam(value = "file",required = true)MultipartFile multipartFile,
						HttpServletResponse response) throws IOException{
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		try{
			LOGGER.info("上传新的文件,name={},size={}",multipartFile.getOriginalFilename(),multipartFile.getSize());
			//目标文件夹
			String folder = this.getFolder() + "";
			Path targetFolder = Paths.get(this.local,folder);
			if(!Files.exists(targetFolder)){
				//目标文件夹不存在,创建之
				Files.createDirectories(targetFolder);
			}
			String originalFilename = multipartFile.getOriginalFilename();
			int index = originalFilename.lastIndexOf(".");
			if(index != -1) {
				//截取后缀 & 重命名
				originalFilename = DateUtils.currentDateTime("yyyyMMddHHmmss") + originalFilename.substring(index);
			}
			//目标文件
			String fileName = GeneralUtils.getUUID() + "_" + originalFilename;
			//写入
			Files.write(targetFolder.resolve(Paths.get(fileName)), multipartFile.getBytes(), StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);
			String filePath = this.prefix + folder + "/" + fileName;
			response.getWriter().write(filePath);
			LOGGER.info("filePath = {}",filePath);
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().write(ERROR_MESSAGE);
		}
		response.flushBuffer();
	}
	
	/**
	 * 文件夹切割为10个
	 * @return
	 */
	private Integer getFolder(){
		Integer index = this.atomicInteger.getAndIncrement();
		if(index.equals(10)){
			this.atomicInteger.set(0);
		}
		return index;
	}
}
