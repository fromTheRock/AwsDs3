package com.dad.AwsDs3.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.dad.AwsDs3.AwsCliCmd;
import com.dad.AwsDs3.controller.AwsDs3Controller;
import com.dad.AwsDs3.dto.UploadData;
import com.dad.AwsDs3.services.interfaces.IMultipartUploads;

public class MultipartUploadAwsCliService implements IMultipartUploads {



	@Override
	public List<UploadData> getMultipartUpload() {
		Process p = null;
		BufferedReader r = null;
		try {
			p = Runtime.getRuntime().exec(AwsCliCmd.LIST_MULTIPART_UPLOADS);
			//r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			r = new BufferedReader(new 
				     InputStreamReader(p.getInputStream()));
			p.waitFor();        

		} 
		catch (IOException | InterruptedException ex) {
			System.out.println("FAILED: " + ex.getMessage());
		}
		String json = getString(r);
		if (json != null) {
			return AwsDs3Controller.getUploadsParser().parseUpkoadsJson(json);
		}

		return null;
	}

	private String getString(BufferedReader r) {
		String json = null;
		if (r != null) {
			try {
				String line;
				line = r.readLine();
				if (line != null)
					json = line; 
				line = r.readLine();
				while (line != null) {
					json += line; 
					line = r.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return json;
	}

}
