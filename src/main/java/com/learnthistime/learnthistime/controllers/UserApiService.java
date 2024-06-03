package com.learnthistime.learnthistime.controllers;

import com.learnthistime.learnthistime.managers.UserManager;
import com.learnthistime.learnthistime.models.User;

import com.learnthistime.learnthistime.service.UserRepoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApiService {
	private UserManager manager;
	UserRepoService mUserRepoService;
	public UserApiService(UserRepoService userRepoService) {
		manager = UserManager.getInstance(userRepoService);
	}
	
	@PostMapping()
	public String addUser(@RequestBody User user) {
		if(manager.addUser(user)) return "Added";
		return "Unable to add user";
	}

	@PostMapping("/friend")
	public String addFriend(@RequestBody AddFriendRequestBody requestBody) {
		System.out.println("requestBody: " + requestBody);
		String user1 = requestBody.user1, user2 = requestBody.user2;
		System.out.println("Got friend request for: " + user1 + " " + user2);
		if (manager.addFriend(user1, user2)) return "Friend added";
		else return "Unable to add friend";
	}

//	@PostMapping()
//	public String addUsers(@RequestBody User users[]) {
//		if (manager.addUsers(users)) return "Added all users";
//		return "Unable to add users";
//	}
	
	@PutMapping()
	public String updateUser(@RequestBody User user) {
		if(manager.updateUser(user)) return "Updated";
		return "Unable to update user";
	}
	
	@DeleteMapping("{userName}")
	public String deleteUser(@PathVariable("userName") String userName) {
		if (manager.deleteUser(userName)) return "Deleted";
		return "Unable to delete";
	}
	
	@GetMapping()
	public User[] getAllUsers() {
		return manager.getAllUsers();
	}

	private static class AddFriendRequestBody {
		String user1;
		String user2;
		public AddFriendRequestBody(String user1, String user2) {
			this.user1 = user1;
			this.user2 = user2;
		}
	}
}
