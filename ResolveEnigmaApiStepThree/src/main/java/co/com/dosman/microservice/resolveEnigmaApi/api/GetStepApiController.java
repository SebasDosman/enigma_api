package co.com.dosman.microservice.resolveEnigmaApi.api;

import co.com.dosman.microservice.resolveEnigmaApi.model.ErrorDetail;
import co.com.dosman.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.dosman.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.dosman.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.dosman.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.dosman.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-28T01:53:31.617-05:00[America/Bogota]")
@Controller
public class GetStepApiController implements GetStepApi {
	private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> getStep(@ApiParam(value = "request body get enigma step", required = true) @Valid @RequestBody JsonApiBodyRequest body) {
        if (!body.getData().get(0).getStep().equalsIgnoreCase("3")) {
        	return new ResponseEntity<>(createResponseErrors(body), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(createResponseSuccess(body), HttpStatus.OK);
    }
    
    private List<JsonApiBodyResponseErrors> createResponseErrors(JsonApiBodyRequest body) {
    	ErrorDetail errorDetail = new ErrorDetail();
    	errorDetail.setCode("003");
    	errorDetail.setDetail("Step: " + body.getData().get(0).getStep() + " not supported - Expected: 3");
    	errorDetail.setId(body.getData().get(0).getHeader().getId());
    	errorDetail.setSource("/getStep");
    	errorDetail.setStatus("400");
    	errorDetail.setTitle("Step not supported");
    	
    	JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
    	responseError.addErrorsItem(errorDetail);

    	List<JsonApiBodyResponseErrors> responseErrorsList = new ArrayList<JsonApiBodyResponseErrors>(); 
    	responseErrorsList.add(responseError);
    	
    	return responseErrorsList;
    }
    
    private List<JsonApiBodyResponseSuccess> createResponseSuccess(JsonApiBodyRequest body) {
        GetEnigmaStepResponse responseEnigma = new GetEnigmaStepResponse();    
        responseEnigma.setHeader(body.getData().get(0).getHeader());
        responseEnigma.setStep(body.getData().get(0).getStep());
        responseEnigma.setStepDescription("Close the door");
        
        JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
        responseSuccess.addDataItem(responseEnigma);

        List<JsonApiBodyResponseSuccess> responseSuccessList = new ArrayList<JsonApiBodyResponseSuccess>();  
        responseSuccessList.add(responseSuccess);
        
        return responseSuccessList;
    }
}
