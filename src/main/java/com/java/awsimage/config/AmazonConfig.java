package com.java.awsimage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
public class AmazonConfig {

	@Bean
	public AmazonS3 s3() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(
				"AWSAccessKeyId", "AWSSecretKey");
		
		return AmazonS3Client.builder()
				.withRegion(Regions.SA_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.build();
	}
}
