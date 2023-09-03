package com.lessons.spring_cache.repository;

import com.lessons.spring_cache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}