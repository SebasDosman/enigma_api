package co.com.dosman.microservice.resolveEnigmaApi.api;

import co.com.dosman.microservice.resolveEnigmaApi.model.ErrorDetail;
import co.com.dosman.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.dosman.microservice.resolveEnigmaApi.model.Header;
import co.com.dosman.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.dosman.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.dosman.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import co.com.dosman.microservice.resolveEnigmaApi.services.RestTemplateService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-28T01:53:31.617-05:00[America/Bogota]")
@Controller
public class GetStepApiController implements GetStepApi {
	private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    private final RestTemplateService restTemplateService;

	@org.springframework.beans.factory.annotation.Autowired
    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request, RestTemplateService restTemplateService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.restTemplateService = restTemplateService;
    }

    public ResponseEntity<?> getStep(@ApiParam(value = "request body get enigma step", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
    	if (!body.getData().get(0).getEnigma().equalsIgnoreCase("How to put a giraffe into a refrigerator?")) {        	
        	return new ResponseEntity<>(createResponseErrors(body), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(createResponseSuccess(body), HttpStatus.OK);
    }
    
    private List<JsonApiBodyResponseErrors> createResponseErrors(JsonApiBodyRequest body) {
    	ErrorDetail errorDetail = new ErrorDetail();
    	errorDetail.setCode("000");
    	errorDetail.setDetail("Enigma: " + body.getData().get(0).getEnigma() + " not supported - Expected: How to put a giraffe into a refrigerator?");
    	errorDetail.setId(body.getData().get(0).getHeader().getId());
    	errorDetail.setSource("/getStep");
    	errorDetail.setStatus("400");
    	errorDetail.setTitle("Enigma not supported");
    	
    	JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
    	responseError.addErrorsItem(errorDetail);
    	
    	List<JsonApiBodyResponseErrors> responseErrorsList = new ArrayList<JsonApiBodyResponseErrors>(); 
    	responseErrorsList.add(responseError);
    	
    	return responseErrorsList;
    }
    
    private List<JsonApiBodyResponseSuccess> createResponseSuccess(JsonApiBodyRequest body) {
        GetEnigmaStepResponse responseEnigma = new GetEnigmaStepResponse();    
        responseEnigma.setHeader(body.getData().get(0).getHeader());
//        String bodyOne = "{\"data\": [{\"header\": {\"id\": \"12345\",\"type\": \"TestGiraffeRefrigerator\"},\"step\": \"1\"}]}";
//        ResponseEntity<?> stepOne = restTemplateService.postStep("http://localhost:8081/v1/getOneEnigma/getStep", bodyOne);
        ResponseEntity<String> stepOne = restTemplateService.getStep("http://localhost:8081/v1/getOneEnigma/getStepOne");
//        String bodyTwo = "{\"data\": [{\"header\": {\"id\": \"12345\",\"type\": \"TestGiraffeRefrigerator\"},\"step\": \"2\"}]}";
//        ResponseEntity<?> stepTwo = restTemplateService.postStep("http://localhost:8082/v1/getOneEnigma/getStep", bodyTwo);
        ResponseEntity<String> stepTwo = restTemplateService.getStep("http://localhost:8082/v1/getOneEnigma/getStepTwo");
//        String bodyThree = "{\"data\": [{\"header\": {\"id\": \"12345\",\"type\": \"TestGiraffeRefrigerator\"},\"step\": \"3\"}]}";
//        ResponseEntity<?> stepThree = restTemplateService.postStep("http://localhost:8083/v1/getOneEnigma/getStep", bodyThree);
        ResponseEntity<String> stepThree = restTemplateService.getStep("http://localhost:8083/v1/getOneEnigma/getStepThree");
        responseEnigma.setAnswer(stepOne.getBody() + " - " + stepTwo.getBody() + " - " + stepThree.getBody());
        
        JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
        responseSuccess.addDataItem(responseEnigma);

        List<JsonApiBodyResponseSuccess> responseSuccessList = new ArrayList<JsonApiBodyResponseSuccess>();  
        responseSuccessList.add(responseSuccess);
        
        return responseSuccessList;
    }
}
