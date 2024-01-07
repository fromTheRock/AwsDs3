package com.dad.AwsDs3.dto;

import java.util.List;

public class MultipartKeyCheckerData {
	
	public String bucket;
    public String KeyMarker;
    public String UploadIdMarker;
    public String NextKeyMarker;
    public String Prefix;
    public String Delimiter;
    public String NextUploadIdMarker;
    public Integer MaxUploads;
    public boolean IsTruncated;
    
    List<UploadData> uploads;
    
	public MultipartKeyCheckerData() {
		super();
	}

	public MultipartKeyCheckerData(String bucket, String keyMarker, String uploadIdMarker, String nextKeyMarker,
			String prefix, String delimiter, String nextUploadIdMarker, Integer maxUploads, boolean isTruncated) {
		super();
		this.bucket = bucket;
		KeyMarker = keyMarker;
		UploadIdMarker = uploadIdMarker;
		NextKeyMarker = nextKeyMarker;
		Prefix = prefix;
		Delimiter = delimiter;
		NextUploadIdMarker = nextUploadIdMarker;
		MaxUploads = maxUploads;
		IsTruncated = isTruncated;
	}

    
}
