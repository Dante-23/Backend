package com.learnthistime.learnthistime.repo;

import com.learnthistime.learnthistime.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
