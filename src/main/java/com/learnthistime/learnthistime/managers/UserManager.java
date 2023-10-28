package com.learnthistime.learnthistime.managers;

import com.learnthistime.learnthistime.models.User;
import com.learnthistime.learnthistime.service.UserRepoService;

import java.util.*;

public class UserManager {
	private static UserManager sInstance = null;

	UserRepoService mUserRepoService;
	
	private UserManager(UserRepoService userRepoService) {
		mUserRepoService = userRepoService;
	}
	
	private UserManager(UserManager manager) {}
	
	public static synchronized UserManager getInstance(UserRepoService userRepoService) {
		if (sInstance == null) {
			sInstance = new UserManager(userRepoService);
		}
		return sInstance;
	}
	
	public boolean exists(User user) {
		return false;
	}
	
	public boolean exists(String userName) {
		return false;
	}
	
	public User[] getAllUsers() {
		return mUserRepoService.getAllUsers().toArray(new User[0]);
	}
	
	public boolean updateUser(User user) {
		return false;
	}
	
	public boolean deleteUser(String userName) {
		return false;
	}
	
	public User getUser(String userName) {
		return null;
	}
	
	public boolean addUser(User user) {
		return mUserRepoService.insert(user);
	}

	public boolean addUsers(User users[]) {
		return mUserRepoService.insertMultipleUsers(users);
	}
}
