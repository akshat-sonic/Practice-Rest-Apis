package com.practice.practice.repository;

import com.practice.practice.model.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * CR Please implement this :)
 */
@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
}
