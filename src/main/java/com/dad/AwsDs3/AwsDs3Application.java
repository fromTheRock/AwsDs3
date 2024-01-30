package com.dad.AwsDs3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dad.AwsDs3.controller.AwsDs3Controller;
import com.dad.AwsDs3.dto.UploadData;
import com.dad.AwsDs3.dto.UploadPartData;
import com.dad.AwsDs3.services.MyServicesConfig;
import com.dad.AwsDs3.services.interfaces.IAmazonS3Client;
import com.dad.AwsDs3.services.interfaces.IExecAbort;
import com.dad.AwsDs3.services.interfaces.IListParts;
import com.dad.AwsDs3.services.interfaces.IMultipartUploads;

@SpringBootApplication
public class AwsDs3Application implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(getClass()); 
	
	public static void main(String[] args) {
		SpringApplication.run(AwsDs3Application.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {

		String bucketName = AwsDs3ConfigurationProp.getInstance().getBucketName();
		try {
			IMultipartUploads muSrv = MyServicesConfig.getInstance().getMultipartUploadsService();
			List<UploadData> uploads = AwsDs3Controller.getMultipartUpload().getMultipartUpload();
			for (UploadData up : uploads) {
				//System.out.println(up.toString());
				logger.info(up.toString());
				
				IListParts partSrv = MyServicesConfig.getInstance().getListPartService();
				List<UploadPartData> parts = partSrv.getListParts(bucketName, up.Key, up.UploadId);
				int nPart = 0;
				int nPartOk = 0;
				int nPartKo = 0;
				List<PartETag> listETags = new ArrayList<PartETag>();
				boolean partial = false;
				for (UploadPartData p : parts) {
					while (++nPart < p.partNumber) {
						System.out.println(String.format("  Missing part %d", nPart));
						nPartKo++;
						partial = true;
					}
					System.out.println(String.format("  %s", p.toString()));
					nPart = p.partNumber;
					nPartOk++;
					PartETag etg = new PartETag(p.partNumber, bucketName);
					etg.setETag(p.eTag);
					listETags.add(etg);
				}
				if (partial || nPartOk <= 1) {
					//System.out.println(String.format("Upload %s, with %d parts ok an %d part missing.\nAbort upload %s (y/n)?", up.Key, nPartOk, nPartKo, up.toString()));

					IAmazonS3Client cliSrv = MyServicesConfig.getInstance().getAmazonS3CkientService();
					AmazonS3 cli = cliSrv.getAmazonS3Client();
					
					GetObjectMetadataRequest req = new GetObjectMetadataRequest(bucketName, up.Key);
					ObjectMetadata md = null;
					try {
						md = cli.getObjectMetadata(req);
					} catch (AmazonS3Exception e) {
						logger.warn(String.format("File %s - Generic Error: %s", up.Key, e.getMessage()));
					} catch (Exception e) {
						logger.warn(String.format("File %s not found in %s:\n%s", up.Key, bucketName, e.getMessage()));
					}
					if (md == null) {
						String[] options = new String[] {"y", "n"};
						String msg = String.format("Try to upload %s again? (y/n)?\nThis might take many minutes.", up.Key); 
						String answ = getQuestionAnswer(options, null, msg);
						if (answ.compareToIgnoreCase(options[0]) == 0) {
//							throw new Exception("To be implemented...");
							
							String path = AwsDs3ConfigurationProp.getInstance().getRootLocalCopy(); 
							File fUpload = null;
							if (path != null)
								fUpload = new File(path, up.Key);
							if (fUpload != null) {
								PutObjectRequest request = new PutObjectRequest(bucketName, up.Key, fUpload);
//								ObjectMetadata metadata = new ObjectMetadata();
//								metadata.setContentType("plain/text");
//								metadata.addUserMetadata("title", "someTitle");
//								request.setMetadata(metadata);						}
					            cli.putObject(request);
							}
						}
					}
					
					if (md != null) {
						String md5 = md.getETag();
//					String md5 = md.getContentMD5(); //In Ds3 multipart objectct ContentMD5 ruturns null
						long size = md.getContentLength();
						logger.info(String.format("File %s, Etag-Parts = %s; Size = %d", up.Key, md5, size));
					}
					
					String[] options = new String[] {"y", "n"};
					String question = String.format("Abort upload %s (y/n)?", up.Key);
					String msg = String.format("Upload %s, with %d parts ok an %d part missing.", up.Key, nPartOk, nPartKo, up.toString());
					String answ = getQuestionAnswer(options, msg, question);
					if (answ.compareToIgnoreCase(options[0]) == 0) {
						System.out.println("Exec abort");

						IExecAbort abortSrv = MyServicesConfig.getInstance().getAborMultiparService();
						abortSrv.abortMultipartUpload(bucketName, up.Key, up.UploadId);

					}
				}
				else {
					//System.out.println(String.format("Upload OK %s:\n%d parts ok an %d part missing.", up.Key, nPartOk, nPartKo));
					logger.info(String.format("Upload OK %s:\n%d parts ok an %d part missing.", up.Key, nPartOk, nPartKo));
					
					IAmazonS3Client cliSrv = MyServicesConfig.getInstance().getAmazonS3CkientService();
					AmazonS3 cli = cliSrv.getAmazonS3Client();
					
					CompleteMultipartUploadRequest req = new CompleteMultipartUploadRequest(bucketName, up.Key, up.UploadId, listETags);
					cli.completeMultipartUpload(req);
					logger.info(String.format("Upload %s Completed!", up.Key));
				}
			}
//			if (uploads != null)
//				System.out.append(uploads.toString());
		} catch (Exception e) {
			//System.err.println(e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}

	}


	private String getQuestionAnswer(String[] options, String msg, String question) {
		if (msg != null && !msg.isEmpty())
			logger.info(msg);
		if (question != null && !question.isEmpty())
			System.out.println(question);
		String answ = null;
		Scanner in = new Scanner(System.in);
		StringBuilder optMsg = new StringBuilder("[");
		for (String s : options) {
			optMsg.append(s);
		}
		optMsg.append("]");
		answ = in.next(optMsg.toString());
		return answ;
	}
}
