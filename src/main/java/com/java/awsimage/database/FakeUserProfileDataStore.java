package com.java.awsimage.database;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.java.awsimage.user.UserProfile;

@Repository
public class FakeUserProfileDataStore {

	private static final List<UserProfile> USER_PROFILES = new ArrayList<>();
	
	static {
		USER_PROFILES.add(new UserProfile(UUID.fromString("d92028b8-b9a8-4b85-8c88-3e163c4b02ca"), 
				"jeffbezos", null));
		USER_PROFILES.add(new UserProfile(UUID.fromString("2810cda8-ebe2-4938-893a-4036a6aead0b"),
				"richardhendricks", null));
	}
	
	public List<UserProfile> getUserProfiles(){
		return USER_PROFILES;
	}
}
