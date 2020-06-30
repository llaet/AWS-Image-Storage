package com.java.awsimage.user.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.java.awsimage.database.FakeUserProfileDataStore;
import com.java.awsimage.user.UserProfile;

@Repository
public class UserProfileRepository {

	private final FakeUserProfileDataStore FAKE_REPOSITORY;
	
	@Autowired
	public UserProfileRepository(FakeUserProfileDataStore fakeRepository) {
		this.FAKE_REPOSITORY = fakeRepository;
	}
	
	public List<UserProfile> findAll(){
		return this.FAKE_REPOSITORY.getUserProfiles();
	}
}
