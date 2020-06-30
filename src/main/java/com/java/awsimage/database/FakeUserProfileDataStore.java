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
		USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "jeffbezos", null));
		USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "richardhendricks", null));
	}
	
	public List<UserProfile> getUserProfiles(){
		return USER_PROFILES;
	}
}
