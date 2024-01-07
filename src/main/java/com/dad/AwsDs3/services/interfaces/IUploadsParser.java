package com.dad.AwsDs3.services.interfaces;

import java.util.List;

import com.dad.AwsDs3.dto.MultipartKeyCheckerData;
import com.dad.AwsDs3.dto.UploadData;

public interface IUploadsParser {

	public List<UploadData> parseUpkoadsJson(String json);
	
}
