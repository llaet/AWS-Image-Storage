package com.java.awsimage.user.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.awsimage.bucket.BucketName;
import com.java.awsimage.filestore.FileStorageServiceImpl;
import com.java.awsimage.user.UserProfile;
import com.java.awsimage.user.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl {

	private final UserProfileRepository repository;
	private final FileStorageServiceImpl fileStorage;
	
	@Autowired
	public UserProfileServiceImpl(UserProfileRepository repository, 
			FileStorageServiceImpl fileStorage) {
		this.repository = repository;
		this.fileStorage = fileStorage;
	}
	
	public List<UserProfile> findAll(){
		return this.repository.findAll();
	}
	

	public byte[] downloadUserProfileImage(UUID id) {
		UserProfile userProfile = getUserProfile(id);
		
		String path = String.format("%s/%s", 
				BucketName.PROFILE_IMAGE.getBucketName(), 
				userProfile.getUserId());
		
		return userProfile.getUserProfileImageLink().map(key ->
		fileStorage.download(path, key)).orElse(new byte[0]);
	}

	public void uploadUserProfileImage(UUID id, MultipartFile file) {
		validFileTypeAndEmptiness(file);
		
		//get user verified by id
		UserProfile userProfile = getUserProfile(id);
		
		Map<String, String> metadata = grabMetadata(file);
		
		String path = String.format("%s/%s", 
				BucketName.PROFILE_IMAGE.getBucketName(), userProfile.getUserId());
		String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
		
		try {
			this.fileStorage.save(path, fileName, Optional.of(metadata), file.getInputStream());
			userProfile.setUserProfileImageLink(fileName);
		} catch (IOException e) {
			throw new IllegalStateException();
		}
	}

	private UserProfile getUserProfile(UUID id) {
		UserProfile userProfile = this.repository.findAll()
				.stream().filter(profile -> profile.getUserId().equals(id))
				.findFirst().orElseThrow(() -> new NoSuchElementException());
		return userProfile;
	}

	
	private Map<String, String> grabMetadata(MultipartFile file) {
		Map<String,String> metadata = new HashMap<>();
		metadata.put("content-type", file.getContentType());
		metadata.put("content-length", String.valueOf(file.getSize()));
		return metadata;
	}

	private void validFileTypeAndEmptiness(MultipartFile file) {
		if(!(Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), 
				ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType())
				.contains(file.getContentType())) | file.isEmpty()) {
			
			throw new IllegalStateException();
		}
	}
}
