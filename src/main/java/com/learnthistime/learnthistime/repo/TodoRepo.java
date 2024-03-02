package com.learnthistime.learnthistime.repo;

import com.learnthistime.learnthistime.models.Todo;
import com.learnthistime.learnthistime.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo, Integer> {
    List<Todo> findBymUser(User user);
}
