package com.java.awsimage.bucket;

public enum BucketName {

	PROFILE_IMAGE("your-aws-bucket-name");
	
	private final String bucketName;

	BucketName(String bucketName){
		this.bucketName = bucketName;
	}
		
	public String getBucketName() {
		return bucketName;
	}
}
