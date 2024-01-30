package com.dad.AwsDs3.services;

import com.dad.AwsDs3.services.interfaces.IListParts;

public class GetObjectAttributeService {//implements IListParts {

	
	public GetObjectAttributeService() {
		super();

	}

//	@Override
	public String getMetadataData(String bucketName, String key, String uploadId) throws Exception {
//		List<UploadPartData> listParts = new ArrayList<UploadPartData>();
//		
//		IAmazonS3Client cliSrv = MyServicesConfig.getInstance().getAmazonS3CkientService();
//		AmazonS3 cli = cliSrv.getAmazonS3Client();
//		
//		GetObjectMetadataRequest req = new GetObjectMetadataRequest(bucketName, key);
//		ObjectMetadata md = cli.getObjectMetadata(req);
//		String md5 = md.getContentMD5();
//		
//		
//		List<PartSummary> list = res.getParts();
//		for (PartSummary p : list) {
//			UploadPartData pd = new UploadPartData();
//			pd.eTag = p.getETag();
//			pd.lastModified = DateUtils.toIsoLocalDate(p.getLastModified());
//			pd.partNumber = p.getPartNumber();
//			pd.size = p.getSize();
//			
//			listParts.add(pd);
//		}
//		
//		return listParts;
		return "";
	}
}
