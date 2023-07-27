package com.springboot_demo.awss3crudspringbootrestapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class AwsS3ServiceImpl implements AwsS3Service {

	private AmazonS3 s3Client;

	@Value("${aws.bucket}")
	private String awsBucket;

	@Autowired
	public AwsS3ServiceImpl(AmazonS3 s3Client) {
		this.s3Client = s3Client;
	}

	@Override
	public List<Bucket> getBucketList() throws AmazonServiceException, SdkClientException {
		return s3Client.listBuckets();
	}

	@Override
	public List<S3ObjectSummary> getObjectList() throws AmazonServiceException, SdkClientException {

		List<S3ObjectSummary> objects = null;

		ListObjectsV2Result res = s3Client.listObjectsV2(awsBucket);
		objects = res.getObjectSummaries();
		for (S3ObjectSummary myValue : objects) {
			System.out.print("\n The name of the key is " + myValue.getKey());
			System.out.print("\n The object is " + myValue.getSize() + " bytes");
			System.out.print("\n The owner is " + myValue.getOwner());
		}

		return objects;
	}

}
