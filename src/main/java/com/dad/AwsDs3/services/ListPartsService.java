package com.dad.AwsDs3.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListPartsRequest;
import com.amazonaws.services.s3.model.MultipartUpload;
import com.amazonaws.services.s3.model.PartListing;
import com.amazonaws.services.s3.model.PartSummary;
import com.dad.AwsDs3.dto.UploadData;
import com.dad.AwsDs3.dto.UploadPartData;
import com.dad.AwsDs3.services.interfaces.IAmazonS3Client;
import com.dad.AwsDs3.services.interfaces.IListParts;
import com.dad.util.DateUtils;

public class ListPartsService implements IListParts {

	
	public ListPartsService() {
		super();

	}

	@Override
	public List<UploadPartData> getListParts(String bucketName, String key, String uploadId) throws Exception {
		List<UploadPartData> listParts = new ArrayList<UploadPartData>();
		
		IAmazonS3Client cliSrv = MyServicesConfig.getInstance().getAmazonS3CkientService();
		AmazonS3 cli = cliSrv.getAmazonS3Client();
		ListPartsRequest req = new ListPartsRequest(bucketName, key, uploadId);
		PartListing res =  cli.listParts(req);
		
		List<PartSummary> list = res.getParts();
		for (PartSummary p : list) {
			UploadPartData pd = new UploadPartData();
			pd.eTag = p.getETag();
			pd.lastModified = DateUtils.toIsoLocalDate(p.getLastModified());
			pd.partNumber = p.getPartNumber();
			pd.size = p.getSize();
			
			listParts.add(pd);
		}
		
		return listParts;
	}
}
