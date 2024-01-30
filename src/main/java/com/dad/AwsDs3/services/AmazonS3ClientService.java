package com.dad.AwsDs3.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.util.TextUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListMultipartUploadsRequest;
import com.amazonaws.services.s3.model.ListPartsRequest;
import com.amazonaws.services.s3.model.MultipartUpload;
import com.amazonaws.services.s3.model.MultipartUploadListing;
import com.dad.AwsDs3.AwsCliCmd;
import com.dad.AwsDs3.AwsDs3ConfigurationProp;
import com.dad.AwsDs3.dto.UploadData;
import com.dad.AwsDs3.services.interfaces.IAmazonS3Client;
import com.dad.AwsDs3.services.interfaces.IMultipartUploads;

public class AmazonS3ClientService implements IAmazonS3Client {
	
	private AmazonS3 client = null;
	
	public AmazonS3ClientService() {
		super();
	}
	
	private AWSCredentialsProvider cp = new AWSCredentialsProvider() {
		private AWSCredentials cred = new AWSCredentials() {
			
			@Override
			public String getAWSSecretKey() {
				return AwsDs3ConfigurationProp.getInstance().getPrivateKey();
			}
			
			@Override
			public String getAWSAccessKeyId() {
				return AwsDs3ConfigurationProp.getInstance().getAccessKey();
			}
		};
		
		@Override
		public void refresh() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public AWSCredentials getCredentials() {
			return cred;
		}
	};


	@Override
	public AmazonS3 getAmazonS3Client() throws Exception {

		if (client == null) {
			
			if (TextUtils.isBlank(AwsDs3ConfigurationProp.getInstance().getAccessKey()))
				throw new Exception("AccessKey vuota");
			if (TextUtils.isBlank(AwsDs3ConfigurationProp.getInstance().getPrivateKey()))
				throw new Exception("SecretKey vuota");
			if (TextUtils.isBlank(AwsDs3ConfigurationProp.getInstance().getBucketName()))
				throw new Exception("Nome Bucket non valido");
			

			EndpointConfiguration endpoint = new EndpointConfiguration(AwsCliCmd.DS3_END_POINT, "eu-west-1");
			AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
			builder.setEndpointConfiguration(endpoint);
			builder.setCredentials(cp);
			client = builder.build();
			
		}
		return client;
	}
}
