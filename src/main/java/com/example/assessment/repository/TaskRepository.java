package com.example.assessment.repository;

import com.example.assessment.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select t from Task t where t.deadline >= :deadline")
    List<Task> findForDeadline(LocalDateTime deadline);
}