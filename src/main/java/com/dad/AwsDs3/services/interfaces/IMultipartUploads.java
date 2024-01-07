package com.dad.AwsDs3.services.interfaces;

import java.util.List;

import com.dad.AwsDs3.dto.MultipartKeyCheckerData;
import com.dad.AwsDs3.dto.UploadData;

public interface IMultipartUploads {

	public List<UploadData> getMultipartUpload() throws Exception;
	
}
