package com.harshmishra4.springsecurity.repository;

import com.harshmishra4.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Harsh
 * @created 21/03/2025 - 2:00 AM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String username);
}