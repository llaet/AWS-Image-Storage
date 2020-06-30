package com.java.awsimage.filestore;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class FileStorageServiceImpl {

	private final AmazonS3 s3;
	
	@Autowired
	public FileStorageServiceImpl(AmazonS3 s3) {
		this.s3 = s3;
	}
	
	public void save(String path, 
			String fileName, 
			Optional<Map<String,String>> optionalMetadata,
			InputStream inputStream) {
		
		ObjectMetadata objectMetadata = new ObjectMetadata();
		optionalMetadata.ifPresent(map ->{
			if(!map.isEmpty()) {
				map.forEach(objectMetadata::addUserMetadata);
			}			
		});
		
		try {
			s3.putObject(path, fileName, inputStream, objectMetadata);
		} catch(Exception ex) {
			throw new IllegalStateException("Failed to store file to S3",ex);
		}	
	}
}
