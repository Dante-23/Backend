package com.learnthistime.learnthistime.repo;

import com.learnthistime.learnthistime.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByUserName(String username);
}
