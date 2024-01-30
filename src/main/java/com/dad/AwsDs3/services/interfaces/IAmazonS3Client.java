package com.dad.AwsDs3.services.interfaces;

import com.amazonaws.services.s3.AmazonS3;

public interface IAmazonS3Client {

	public AmazonS3 getAmazonS3Client() throws Exception;
	
}
