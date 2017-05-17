package global.transactions.service;

import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class UtilService {
	
	private static final String urlPost = "api.vertexsms.com/sms";
	
	public static Boolean sendSMS(String mobile) {
		String body = "{ \"to\": \"37069977165\", \"from\": \"Test\", \"message\": \"123456\" }";
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Host","api.vertexsms.com");
		headers.add("Accept","application/json");
		headers.add("X-VertexSMS-Token", "sXr7RzqN0SwREGsg5dk7Ur6TqtgYqQPE80K4E6nu");
		headers.add("Content-Type", "application/json");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<String> request = new HttpEntity<String>(body, headers);

		return restTemplate.postForObject(urlPost, request, Boolean.class);
	}
	
	public static int generatePasscode() {
		Random ran = new Random();
		return (100000 + ran.nextInt(900000));
	}
}
