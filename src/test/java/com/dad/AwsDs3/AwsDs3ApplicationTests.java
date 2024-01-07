package com.dad.AwsDs3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.dad.AwsDs3.dto.UploadData;
import com.dad.AwsDs3.services.UploadsParserService;
import com.dad.AwsDs3.services.interfaces.IUploadsParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class AwsDs3ApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void testUploadsParser() {
		String json = "{"
				+ "    \"Uploads\": [ "
				+ "                { "
				+ "                    \"UploadId\": \"13d6901c-2b4b-4cdb-af12-53ead20e1625\", "
				+ "                    \"Key\": \"Media/Eventi/20191130-ConcertoNomadi-Boves/20191130_233135.mp4\", "
				+ "                    \"Initiated\": \"2023-11-29T23:16:50+00:00\" "
				+ "                }, "
				+ "                { "
				+ "                    \"UploadId\": \"1c16310c-a35b-495b-aee1-d27c1b4a86b0\", "
				+ "                    \"Key\": \"Media/Eventi/20191130-ConcertoNomadi-Boves/20191130_233135.mp4\", "
				+ "                    \"Initiated\": \"2023-11-30T22:32:47.886000+00:00\" "
				+ "                }, "
				+ "                { "
				+ "                    \"UploadId\": \"9d433587-bcf4-4b87-be54-c6000eb57087\", "
				+ "                    \"Key\": \"Media/Eventi/20191130-ConcertoNomadi-Boves/20191130_233135.mp4\", "
				+ "                    \"Initiated\": \"2023-11-09T23:10:37.833000+00:00\" "
				+ "                }, "
				+ "                { "
				+ "                    \"UploadId\": \"da6f0469-9a8f-4674-8f47-1ddedc0f9c31\", "
				+ "                    \"Key\": \"Media/Eventi/20191130-ConcertoNomadi-Boves/20191130_233135.mp4\", "
				+ "                    \"Initiated\": \"2023-11-09T22:56:09.336000+00:00\" "
				+ "                }, "
				+ "                { "
				+ "                    \"UploadId\": \"2f950d09-4d23-4caf-b1dd-5f7171fe7e18\", "
				+ "                    \"Key\": \"Media/Eventi/20191130-ConcertoNomadi-Boves/20191130_234851.mp4\", "
				+ "                    \"Initiated\": \"2023-11-09T23:29:16.893000+00:00\" "
				+ "                }, "
				+ "                { "
				+ "                    \"UploadId\": \"8fab3e94-e7d6-499d-8270-577c594fca79\", "
				+ "                    \"Key\": \"Media/Eventi/20191130-ConcertoNomadi-Boves/20191130_234851.mp4\", "
				+ "                    \"Initiated\": \"2023-11-09T23:25:16.496000+00:00\" "
				+ "                }"
				+ "    ] "
				+ "}";
		json = json.replaceAll(" ", "");

//		Map<String, List<UploadData>> uploads = null;
//		ObjectMapper mapper = new ObjectMapper();
////		JsonParser parser =  JsonParserFactory.getJsonParser();
////		Map<String, Object> obj = null;
////		List<Object> list = null;
		List<UploadData> list = null;
//		try {
////			list = parser.parseList(json);
////			obj = parser.parseMap(json);
//			uploads = mapper.readValue(json, new TypeReference<Map<String, List<UploadData>>>() {
//			});
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
////		list = (List<Object>) obj.get("Uploads");
//		if (uploads != null)
//			list = uploads.get("Uploads");
		IUploadsParser pars = new UploadsParserService();
		list = pars.parseUpkoadsJson(json);
		
		Assert.notNull(list, "Lista vuota!");
//		for (Object o : list) {
//			
//		}
	}
}
