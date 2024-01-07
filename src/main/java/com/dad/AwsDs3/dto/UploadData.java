package com.dad.AwsDs3.dto;

public class UploadData {

    public String UploadId;
    public String Key;
    public String Initiated;
	
    public UploadData() {
		super();
	}

	public UploadData(String uploadId, String key, String initiated) {
		super();
		UploadId = uploadId;
		Key = key;
		Initiated = initiated;
	}
	
	public String toString() {
		return "id: " + UploadId + "\nKey: " + Key + "\nInitiated: " + Initiated;
	}
}
