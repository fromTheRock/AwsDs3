package com.dad.AwsDs3.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.dad.AwsDs3.services.MultipartUploadService;
import com.dad.AwsDs3.services.UploadsParserService;
import com.dad.AwsDs3.services.interfaces.IMultipartUploads;
import com.dad.AwsDs3.services.interfaces.IUploadsParser;

@Controller
public class AwsDs3Controller {


	@Bean
	public static IMultipartUploads getMultipartUpload(){
		return  new MultipartUploadService();
	}

	@Bean
	public static IUploadsParser getUploadsParser(){
		return new UploadsParserService();
	}

}
