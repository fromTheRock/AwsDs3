package com.dad.AwsDs3.services.interfaces;

public interface IExecAbort {

	public void abortMultipartUpload(String bucketName, String key, String uploadId) throws Exception;
	
}
