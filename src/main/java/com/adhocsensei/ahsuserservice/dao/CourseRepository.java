package com.adhocsensei.ahsuserservice.dao;

import com.adhocsensei.ahsuserservice.dto.Course;
import com.adhocsensei.ahsuserservice.dto.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<User> findByUser(User user, Sort sort);
}