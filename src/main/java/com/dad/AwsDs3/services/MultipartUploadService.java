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
import com.amazonaws.services.s3.model.MultipartUpload;
import com.amazonaws.services.s3.model.MultipartUploadListing;
import com.dad.AwsDs3.AwsCliCmd;
import com.dad.AwsDs3.AwsDs3ConfigurationProp;
import com.dad.AwsDs3.dto.UploadData;
import com.dad.AwsDs3.services.interfaces.IAmazonS3Client;
import com.dad.AwsDs3.services.interfaces.IMultipartUploads;

public class MultipartUploadService implements IMultipartUploads {

	private List<UploadData> listUploads;
	
	public MultipartUploadService() {
		super();

		listUploads = new ArrayList<UploadData>();
					
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
	public List<UploadData> getMultipartUpload() throws Exception {
		
		if (TextUtils.isBlank(AwsDs3ConfigurationProp.getInstance().getAccessKey()))
			throw new Exception("AccessKey vuota");
		if (TextUtils.isBlank(AwsDs3ConfigurationProp.getInstance().getPrivateKey()))
			throw new Exception("SecretKey vuota");
		if (TextUtils.isBlank(AwsDs3ConfigurationProp.getInstance().getBucketName()))
			throw new Exception("Nome Bucket non valido");
		

		IAmazonS3Client cliSrv = MyServicesConfig.getInstance().getAmazonS3CkientService();
		AmazonS3 cli = cliSrv.getAmazonS3Client();
		ListMultipartUploadsRequest request = new ListMultipartUploadsRequest(AwsDs3ConfigurationProp.getInstance().getBucketName());
		MultipartUploadListing loads = cli.listMultipartUploads(request);
		
		List<MultipartUpload> list = loads.getMultipartUploads();
		for (MultipartUpload mpu : list) {
			String id = mpu.getUploadId();
		
			String key = mpu.getKey();
			Date init = mpu.getInitiated();
			UploadData upload = new UploadData(id, key, init.toLocaleString());
			listUploads.add(upload);
		}

		return listUploads;
	}
}
