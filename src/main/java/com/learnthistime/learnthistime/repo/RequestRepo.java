package com.learnthistime.learnthistime.repo;

import com.learnthistime.learnthistime.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Request, Integer> {
}
