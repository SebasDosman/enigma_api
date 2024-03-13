package co.com.dosman.microservice.resolveEnigmaApi.services;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import co.com.dosman.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;


@Service
public class RestTemplateService {
	private final RestTemplate restTemplate;

	public RestTemplateService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}	
	
	public ResponseEntity<String> getStep(String url) {
		return restTemplate.getForEntity(url, String.class);
	}
}
