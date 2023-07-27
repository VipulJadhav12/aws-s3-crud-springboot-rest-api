package com.springboot_demo.awss3crudspringbootrestapi.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

@RestControllerAdvice
public class AwsS3ExceptionHandlerController {
	
	@ExceptionHandler
	public Map<String, String> handleException(AmazonS3Exception ex) {
		
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("statusCode", String.valueOf(ex.getStatusCode()));
		errorMap.put("requestId", ex.getRequestId());
		errorMap.put("errorMessage", ex.getMessage());
		
		return errorMap;
	}
	
	@ExceptionHandler
	public Map<String, String> handleException(SdkClientException ex) {
		
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		
		return errorMap;
	}

}
