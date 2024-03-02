package com.learnthistime.learnthistime.service;

import com.learnthistime.learnthistime.models.Todo;
import com.learnthistime.learnthistime.models.User;
import com.learnthistime.learnthistime.repo.TodoRepo;
import com.learnthistime.learnthistime.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoRepoService {
    TodoRepo mTodoRepo;
    TodoRepoService(TodoRepo todoRepo) {
        this.mTodoRepo = todoRepo;
    }

    public boolean insert(Todo todo) {
        try {
            if (mTodoRepo.findById(todo.getId()).isPresent()) {
                return false;
            }
            List<Todo> todos = mTodoRepo.findBymUser(todo.getUser());
            if (todos != null && !todos.isEmpty()) {
                for (Todo cTodo: todos) {
                    if (cTodo.getName().compareTo(todo.getName()) == 0) return false;
                }
            }
            mTodoRepo.save(todo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Todo> getAllTodos() {
        return mTodoRepo.findAll();
    }

    public List<Todo> getAllTodos(final User user) {
        return mTodoRepo.findBymUser(user);
    }
}
