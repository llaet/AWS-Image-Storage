package com.java.awsimage.user.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.awsimage.user.UserProfile;
import com.java.awsimage.user.service.UserProfileServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/profile")
public class UserProfileController {

	@Autowired
	private UserProfileServiceImpl service;
	
	@GetMapping
	public List<UserProfile> findAll(){
		return this.service.findAll();
	}
	
	@PostMapping(
			path = "/{userId}/image/upload",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public void updloadUserProfileImage(@PathVariable("userId") UUID id,
			@RequestParam("file") MultipartFile file) {
		
		this.service.uploadUserProfileImage(id, file);
	}
	
	@GetMapping("/{userId}/image/download")
	public byte[] downloadUserProfileImage(@PathVariable("userId") UUID id){
		return this.service.downloadUserProfileImage(id);
	}
}
