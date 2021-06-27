package com.adhocsensei.ahsuserservice.dao;

import com.adhocsensei.ahsuserservice.dto.Authority;
import com.adhocsensei.ahsuserservice.dto.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {}