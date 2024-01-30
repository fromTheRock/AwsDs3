package com.dad.AwsDs3.dto;

public class UploadPartData {

	public int partNumber;
    public String lastModified;
    public String eTag;
    public long size;
    
    public UploadPartData() {
		super();
	}

    public UploadPartData(int partNumber, String lastModified, String eTag, int size) {
    	super();
    	this.partNumber = partNumber;
    	this.lastModified = lastModified;
    	this.eTag = eTag;
    	this.size = size;
    }
    
	public String toString() {
		return String.format("PartNumber: %d, LastModified: %s, eTag: %s, Size: %d", partNumber, lastModified, eTag, size);
	}
}
