package com.springboot_demo.awss3crudspringbootrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.springboot_demo.awss3crudspringbootrestapi.service.AwsS3Service;

@RestController
@RequestMapping("/api/v1")
public class AwsS3Controller {

	private AwsS3Service awsS3Service;

	@Autowired
	public AwsS3Controller(AwsS3Service awsS3Service) {
		this.awsS3Service = awsS3Service;
	}

	@GetMapping("/bucket-list")
	public ResponseEntity<List<Bucket>> getAwsS3BucketList() throws AmazonS3Exception, SdkClientException {
		
		List<Bucket> bucketList = null;
		try {
			bucketList = awsS3Service.getBucketList();
		} catch (AmazonS3Exception e) {
			// Printing Amazon S3 service level error response.
			System.err.println("An exception occured while making aws request with error code: " + e.getErrorCode() + " - " + e.getErrorMessage());
			throw e;
		} catch (SdkClientException e) {
			// Printing Amazon S3 SDK level error response.
			System.err.println("An exception occured while making aws request with error: " + e.getMessage());
			throw e;
		}

		return new ResponseEntity<>(bucketList, HttpStatus.OK);
	}

	@GetMapping("/bucket-content")
	public ResponseEntity<List<S3ObjectSummary>> getAwsS3ObjectsFromBucket() throws AmazonS3Exception, SdkClientException {
		
		List<S3ObjectSummary> s3ObjectSummary = null;
		try {
			s3ObjectSummary = awsS3Service.getObjectList();
		} catch (AmazonS3Exception e) {
			// Printing Amazon S3 service level error response.
			System.err.println("An exception occured while making aws request with error code: " + e.getStatusCode() + " - " + e.getErrorMessage());
			throw e;
		} catch (SdkClientException e) {
			// Printing Amazon S3 service level error response.
			System.err.println("An exception occured while making aws request with error: " + e.getMessage());
			throw e;
		}
		
		return new ResponseEntity<>(s3ObjectSummary, HttpStatus.OK);
	}

}
