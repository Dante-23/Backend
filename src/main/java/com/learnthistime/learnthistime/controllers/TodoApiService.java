package com.learnthistime.learnthistime.controllers;

import com.learnthistime.learnthistime.service.TodoRepoService;
import com.learnthistime.learnthistime.service.UserRepoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learnthistime.learnthistime.managers.TodoManager;
import com.learnthistime.learnthistime.managers.UserManager;
import com.learnthistime.learnthistime.models.Todo;
import com.learnthistime.learnthistime.models.User;

import java.util.logging.Logger;

@RestController
@RequestMapping("/todo")
public class TodoApiService {
	private TodoManager todoManager;
	private UserManager userManager;
	private TodoRepoService mTodoRepoService;
	private UserRepoService mUserRepoService;
	public TodoApiService(UserRepoService userRepoService, TodoRepoService todoRepoService) {
		mUserRepoService = userRepoService;
		userManager = UserManager.getInstance(mUserRepoService);
		todoManager = TodoManager.getInstance(todoRepoService);
	}
	
	@GetMapping("{userName}")
	public Todo[] getAllTodo(@PathVariable("userName") String userName) {
		User user = userManager.getUser(userName);
		if (user == null) return null;
		return todoManager.getAllTodo(user);
	}
	
	@PostMapping()
	public String addTodo(@RequestBody TodoAddRequest todoRequest) {
		User user = userManager.getUser(todoRequest.userName);
		if (user == null) return "Unable to add. User does not exists.";
		Todo todo = new Todo(user, todoRequest.todo.getName(), todoRequest.todo.getDueDate());
		if (todoManager.addTodo(todo))
			return "Todo added";
		else return "Unable to add";
	}

//	@PostMapping()
//	public String addTodo(@RequestBody Todo todo) {
//		if (todoManager.addTodo(todo))
//			return "Todo added";
//		else return "Unable to add";
//	}
	
	@DeleteMapping()
	public String deleteTodo(@RequestBody TodoDeleteRequest request) {
		if (todoManager.deleteTodo(userManager.getUser(request.userName), request.todoId))
			return "Deleted";
		return "Unable to delete";
	}
	
	private static class TodoAddRequest {
		String userName;
		Todo todo;
		@SuppressWarnings("unused")
		public TodoAddRequest(String userName, Todo todo) {
			this.userName = userName;
			this.todo = todo;
		}
	}
	
	private static class TodoDeleteRequest {
		String userName;
		int todoId;
		@SuppressWarnings("unused")
		public TodoDeleteRequest(String userName, int todoId) {
			this.userName = userName;
			this.todoId = todoId;
		}
	}
}
