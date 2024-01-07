
package com.dad.AwsDs3.services;

import java.util.List;
import java.util.Map;

import com.dad.AwsDs3.dto.UploadData;
import com.dad.AwsDs3.services.interfaces.IUploadsParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UploadsParserService implements IUploadsParser {

	@Override
	public List<UploadData> parseUpkoadsJson(String json) {

		Map<String, List<UploadData>> uploads = null;
		ObjectMapper mapper = new ObjectMapper();
		List<UploadData> list = null;
		try {
			uploads = mapper.readValue(json, new TypeReference<Map<String, List<UploadData>>>() {});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (uploads != null) {
			list = uploads.get("Uploads");
			return list;
		}
		return null;
	}

}
