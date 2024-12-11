package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Get all tasks with optional filter by status
    @GetMapping
    public String getAllTasks(@RequestParam(value = "status", required = false) TaskStatus status, Model model) {
        List<Task> tasks;
        if (status != null) {
            tasks = taskService.filterTasksByStatus(status);
        } else {
            tasks = taskService.getAllTasks();
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new Task()); // For form binding
        return "taskList"; // Make sure this corresponds to your taskList.html
    }

    // Show form to edit a task
    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "editTask"; // Make sure this corresponds to your editTask.html
    }

    // Post a task edit
    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, @ModelAttribute Task task, RedirectAttributes redirectAttributes) {
        taskService.updateTask(id, task);
        redirectAttributes.addFlashAttribute("message", "Task updated successfully!");
        return "redirect:/tasks";
    }

    // Create a new task
    @PostMapping
    public String createTask(@ModelAttribute Task task, RedirectAttributes redirectAttributes) {
        taskService.createTask(task);
        redirectAttributes.addFlashAttribute("message", "Task created successfully!");
        return "redirect:/tasks";
    }

    // Delete a task by its ID
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.deleteTask(id);
        redirectAttributes.addFlashAttribute("message", "Task deleted successfully!");
        return "redirect:/tasks";
    }
}
