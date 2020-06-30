package com.java.awsimage.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
