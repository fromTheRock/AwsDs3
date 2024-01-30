package com.dad.AwsDs3.services.interfaces;

import java.util.List;

import com.dad.AwsDs3.dto.UploadPartData;

public interface IListParts {

	public List<UploadPartData> getListParts(String bucketName, String key, String uploadId) throws Exception;
	
}
