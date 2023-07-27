package com.springboot_demo.awss3crudspringbootrestapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {
	
	@Value("${aws.region}")
	private String awsRegion;
	
	@Value("${aws.bucket}")
	private String awsBucket;
	
	@Value("${aws.profile}")
	private String awsProfile;
	
	private AmazonS3 s3Client;
	
	@Bean(name = "s3Client")
	public AmazonS3 getS3CLient() {
		
		s3Client = AmazonS3ClientBuilder.standard()
								.withRegion(awsRegion)
								.withCredentials(new ProfileCredentialsProvider(awsProfile))
								.build();
		
		return s3Client;
	}
	
}
