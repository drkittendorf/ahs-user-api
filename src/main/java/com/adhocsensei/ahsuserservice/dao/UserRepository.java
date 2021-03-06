package com.adhocsensei.ahsuserservice.dao;

import com.adhocsensei.ahsuserservice.dto.Course;
import com.adhocsensei.ahsuserservice.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}