package com.java.awsimage.user;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {

	private UUID userId;
	private String username;
	private String userProfileImageLink;
	
	public UserProfile(UUID userId, String username, String userProfileImageLink) {
		super();
		this.userId = userId;
		this.username = username;
		this.userProfileImageLink = userProfileImageLink;
	}
	
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Optional<String> getUserProfileImageLink() {
		return Optional.ofNullable(userProfileImageLink);
	}
	public void setUserProfileImageLink(String userProfileImageLink) {
		this.userProfileImageLink = userProfileImageLink;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, username, userProfileImageLink);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		UserProfile that = (UserProfile) obj;
		
		return Objects.equals(userId, that.userId) &&
				Objects.equals(username, that.username) &&
				Objects.equals(userProfileImageLink, that.userProfileImageLink);
	}
		
}
