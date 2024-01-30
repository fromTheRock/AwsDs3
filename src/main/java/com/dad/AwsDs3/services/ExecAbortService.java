package com.dad.AwsDs3.services;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.dad.AwsDs3.dto.UploadPartData;
import com.dad.AwsDs3.services.interfaces.IAmazonS3Client;
import com.dad.AwsDs3.services.interfaces.IExecAbort;

public class ExecAbortService implements IExecAbort {

	
	public ExecAbortService() {
		super();

	}

	@Override
	public void abortMultipartUpload(String bucketName, String key, String uploadId) throws Exception {
		List<UploadPartData> listParts = new ArrayList<UploadPartData>();
		
		IAmazonS3Client cliSrv = MyServicesConfig.getInstance().getAmazonS3CkientService();
		AmazonS3 cli = cliSrv.getAmazonS3Client();
		
		AbortMultipartUploadRequest req = new AbortMultipartUploadRequest(bucketName, key, uploadId);
		cli.abortMultipartUpload(req);
	}
}
