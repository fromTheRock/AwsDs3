package com.dad.AwsDs3.services;

import org.springframework.context.annotation.Bean;

import com.dad.AwsDs3.services.interfaces.IAmazonS3Client;
import com.dad.AwsDs3.services.interfaces.IExecAbort;
import com.dad.AwsDs3.services.interfaces.IListParts;
import com.dad.AwsDs3.services.interfaces.IMultipartUploads;
import com.dad.AwsDs3.services.interfaces.IUploadsParser;

//TODO: @Configuration
public class MyServicesConfig {

	private static MyServicesConfig instance = null;
	
//TODO:  @Value("${service-type}")
//  MyServiceTypes myServiceType;
	private IMultipartUploads multipartUploadsService = null;
	private IAmazonS3Client amazonS3ClientService = null;
	private IUploadsParser uploadsParserService = null;
	private IListParts listPartsService = null;
	private IExecAbort abortMultiparService = null;


	private MyServicesConfig() {
		super();
	}

	public static MyServicesConfig getInstance() {
		if (instance == null)
			instance = new MyServicesConfig();
		return instance;
	}

	@Bean
	public IMultipartUploads getMultipartUploadsService() {
//TODO:    if (myServiceType == MyServiceTypes.One) {
//      return new MyServiceImp1();
//    } else {
//      return new MyServiceImp2();
//    }
		if (multipartUploadsService == null)
			multipartUploadsService = new MultipartUploadService();
		return multipartUploadsService;
	}


	@Bean
	public IAmazonS3Client getAmazonS3CkientService() {
//TODO:    if (myServiceType == MyServiceTypes.One) {
//      return new MyServiceImp1();
//    } else {
//      return new MyServiceImp2();
//    }
		if (amazonS3ClientService == null)
			amazonS3ClientService = new AmazonS3ClientService();
		return amazonS3ClientService;
	}
	
	@Bean
	public IUploadsParser getUploadsParser() {
		if (uploadsParserService == null)
			uploadsParserService = new UploadsParserService();
		return uploadsParserService;
	}
	
	@Bean
	public IListParts getListPartService() {
		if (listPartsService == null)
			listPartsService = new ListPartsService();
		return listPartsService;
	}

	@Bean
	public IExecAbort getAborMultiparService() {
		if (abortMultiparService == null)
			abortMultiparService = new ExecAbortService();
		return abortMultiparService;
	}
}