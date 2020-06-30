package com.java.awsimage.bucket;

public enum BucketName {

	PROFILE_IMAGE("aws-image-hub");
	
	private final String bucketName;

	BucketName(String bucketName){
		this.bucketName = bucketName;
	}
		
	public String getBucketName() {
		return bucketName;
	}
}
