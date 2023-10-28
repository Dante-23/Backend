package com.learnthistime.learnthistime.repo;

import com.learnthistime.learnthistime.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo, Integer> {
}
