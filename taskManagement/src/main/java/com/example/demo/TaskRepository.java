package com.example.demo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status); // Query method to filter tasks by status
}
