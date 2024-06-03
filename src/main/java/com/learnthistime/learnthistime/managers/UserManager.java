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

	public static synchronized UserManager getAlreadyCreatedInstance() {
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
		return mUserRepoService.getUserFromUserName(userName);
	}
	
	public boolean addUser(User user) {
		return mUserRepoService.insert(user);
	}

	public boolean addUsers(User users[]) {
		return mUserRepoService.insertMultipleUsers(users);
	}

	public boolean addFriend(final String userName1, final String userName2) {
		User user1 = mUserRepoService.getUserFromUserName(userName1);
		User user2 = mUserRepoService.getUserFromUserName(userName2);
		user1.addFriend(user2);
		user2.addFriend(user1);
		if (mUserRepoService.updateUser(user1) && mUserRepoService.updateUser(user2))
			return true;
		else return false;
	}
}
