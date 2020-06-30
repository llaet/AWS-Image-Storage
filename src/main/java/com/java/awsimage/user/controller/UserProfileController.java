package com.java.awsimage.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.awsimage.user.UserProfile;
import com.java.awsimage.user.service.UserProfileServiceImpl;

@RestController
@RequestMapping("/api/v1/profile")
public class UserProfileController {

	@Autowired
	private UserProfileServiceImpl service;
	
	@GetMapping
	public List<UserProfile> findAll(){
		return this.service.findAll();
	}
}
