package com.dad.AwsDs3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.dad.AwsDs3.controller.AwsDs3Controller;
import com.dad.AwsDs3.dto.UploadData;

@SpringBootApplication
public class AwsDs3Application implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(AwsDs3Application.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {


		try {
			List<UploadData> uploads = AwsDs3Controller.getMultipartUpload().getMultipartUpload();
			if (uploads != null)
				System.out.append(uploads.toString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
