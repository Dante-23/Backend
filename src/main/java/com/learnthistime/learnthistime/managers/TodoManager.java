package com.learnthistime.learnthistime.managers;

import com.learnthistime.learnthistime.models.*;
import com.learnthistime.learnthistime.service.TodoRepoService;

import java.util.concurrent.*;
import java.util.*;

public class TodoManager {
	private static TodoManager sInstance;
	private TodoRepoService mTodoRepoService;
	private TodoManager(TodoRepoService repoService) {
		mTodoRepoService = repoService;
	}
	private TodoManager(TodoManager manager) {}
	public static TodoManager getInstance(TodoRepoService repoService) {
		if (sInstance == null) {
			sInstance = new TodoManager(repoService);
		}
		return sInstance;
	}
	
//	public Todo[] getAllTodo(User user) {
//		if (user == null || !userTodoMap.containsKey(user)) return null;
//		Object objarr[] = userTodoMap.get(user).toArray();
//		return Arrays.copyOf(objarr, objarr.length, Todo[].class);
//	}

	public Todo[] getAllTodo() {
		return mTodoRepoService.getAllTodos().toArray(new Todo[0]);
	}
	
	public int userTodoExists(User user, int todoId) {
//		Todo todos[] = getAllTodo(user);
//		if (todos == null) return -1;
//		for (int i = 0; i < todos.length; i++) {
//			if (todos[i].getId() == todoId) return i;
//		}
		return -1;
	}
	
//	public boolean addTodo(User user, Todo todo) {
//		if (todo == null || user == null) return false;
//		todo.setId(nextId++);
//		if (!userTodoMap.containsKey(user))
//			userTodoMap.put(user, new Vector<>());
//		userTodoMap.get(user).add(todo);
//		return true;
//	}

	public boolean addTodo(Todo todo) {
		return mTodoRepoService.insert(todo);
	}
	
	public boolean deleteTodo(User user, int todoId) {
//		int index = userTodoExists(user, todoId);
//		if (index == -1) return false;
//		userTodoMap.get(user).remove(index);
		return true;
	}
}
