package co.com.dosman.microservice.resolveEnigmaApi.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RestTemplateService {
	private final RestTemplate restTemplate;

	public RestTemplateService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}	
	
	public ResponseEntity<String> getStep(String url) {
		return restTemplate.getForEntity(url, String.class);
	}
	
	public ResponseEntity<?> postStep(String url, String body) {
		HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request= new HttpEntity<>(body, header);
        
		return restTemplate.postForEntity(url, request, String.class);
	}
}
