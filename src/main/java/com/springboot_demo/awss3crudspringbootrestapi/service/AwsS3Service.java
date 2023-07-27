package com.springboot_demo.awss3crudspringbootrestapi.service;

import java.util.List;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public interface AwsS3Service {
	
	public List<Bucket> getBucketList();
	
	public List<S3ObjectSummary> getObjectList();

}
