package com.java.awsimage.user.service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.awsimage.user.UserProfile;
import com.java.awsimage.user.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl {

	private final UserProfileRepository repository;
	
	@Autowired
	public UserProfileServiceImpl(UserProfileRepository repository) {
		this.repository = repository;
	}
	
	public List<UserProfile> findAll(){
		return this.repository.findAll();
	}

	public void uploadUserProfileImage(UUID id, MultipartFile file) {
		if(!(Arrays.asList(ContentType.IMAGE_JPEG, ContentType.IMAGE_PNG)
				.contains(file.getContentType())) | file.isEmpty()) {
			
			throw new IllegalStateException();
		}
		
		UserProfile userProfile = this.repository.findAll()
				.stream().filter(profile -> profile.getUserId().equals(id))
				.findFirst().orElseThrow(() -> new NoSuchElementException());
		
		userProfile.setUserProfileImageLink(file.getOriginalFilename());
	}
}
