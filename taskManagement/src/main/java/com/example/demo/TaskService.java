package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    // Injecting the TaskRepository to interact with the database (Replace with actual JPA Repository)
    private final TaskRepository taskRepository;

    // In-memory mock task list
    private List<Task> taskList = new ArrayList<>();

    // Constructor injection for task repository
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

        // For mock purposes, pre-fill the list with some tasks
        taskList.add(new Task(1L, "Task 1", "Description of Task 1", TaskStatus.PENDING));
        taskList.add(new Task(2L, "Task 2", "Description of Task 2", TaskStatus.COMPLETED));
        taskList.add(new Task(3L, "Task 3", "Description of Task 3", TaskStatus.IN_PROGRESS));
    }

    /**
     * Get all tasks
     * This method would typically fetch data from the database.
     */
    public List<Task> getAllTasks() {
        return taskList;  // Replace with taskRepository.findAll() in a real application
    }

    /**
     * Create a new task
     * This method would persist the task to the database.
     */
    public void createTask(Task task) {
        // In a real scenario, save to the database
        task.setId((long) (taskList.size() + 1));  // Mock task ID generation
        taskList.add(task);  // Add the task to the in-memory list (replace with taskRepository.save())
    }

    /**
     * Get task by ID
     * This method would typically fetch a task from the database by its ID.
     */
    public Task getTaskById(Long id) {
        // Use the repository to find the task by ID
        return taskList.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);  // Return null if task is not found (in a real app, throw an exception)
    }

    /**
     * Update task
     * This method would typically update a task in the database.
     */
    public void updateTask(Long id, Task task) {
        // Find the task by ID (in a real app, you'd use the repository)
        Task existingTask = getTaskById(id);
        if (existingTask != null) {
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
        }
    }

    /**
     * Delete a task by ID
     * This method would typically delete a task from the database.
     */
    public void deleteTask(Long id) {
        // Remove the task from the list (in a real app, you'd use taskRepository.deleteById(id))
        taskList.removeIf(task -> task.getId().equals(id));
    }

    /**
     * Filter tasks by status
     * This method would filter tasks based on their status.
     */
    public List<Task> filterTasksByStatus(TaskStatus status) {
        // Use stream filtering to get tasks by status
        return taskList.stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }
}
