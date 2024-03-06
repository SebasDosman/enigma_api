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
    	GetEnigmaStepResponse responseEnigma = new GetEnigmaStepResponse();    
        JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
        List<JsonApiBodyResponseSuccess> responseSuccessList = new ArrayList<JsonApiBodyResponseSuccess>();            
        
        if (!body.getData().get(0).getStep().equalsIgnoreCase("1")) {
        	JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
        	List<JsonApiBodyResponseErrors> responseErrorsList = new ArrayList<JsonApiBodyResponseErrors>(); 
        	
        	ErrorDetail errorDetail = new ErrorDetail();
        	errorDetail.setCode("001");
        	errorDetail.setDetail("Step: " + body.getData().get(0).getStep() + " not supported - Expected: 1");
        	errorDetail.setId(body.getData().get(0).getHeader().getId());
        	errorDetail.setSource("/getStep");
        	errorDetail.setStatus("400");
        	errorDetail.setTitle("Step not supported");
        	
        	responseError.addErrorsItem(errorDetail);
        	responseErrorsList.add(responseError);
        	
        	return new ResponseEntity<>(responseErrorsList, HttpStatus.BAD_REQUEST);
        }
        
        responseEnigma.setHeader(body.getData().get(0).getHeader());
        responseEnigma.setStep(body.getData().get(0).getStep());
        responseEnigma.setStepDescription("Open the refrigerator");
        
        responseSuccess.addDataItem(responseEnigma);
        responseSuccessList.add(responseSuccess);
        
        return new ResponseEntity<>(responseSuccessList, HttpStatus.OK);
    }
}
