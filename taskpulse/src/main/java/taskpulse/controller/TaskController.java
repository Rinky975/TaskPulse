package taskpulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import taskpulse.entity.Task;
import taskpulse.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import taskpulse.dto.DashboardResponse;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getMyTasks() {
        return taskService.getMyTasks();
    }
    @PutMapping("/{id}")
public Task updateTask(@PathVariable Long id,
                       @RequestBody Task task) {
    return taskService.updateTask(id, task);
}
@DeleteMapping("/{id}")
public String deleteTask(@PathVariable Long id) {

    taskService.deleteTask(id);

    return "Task Deleted Successfully";
}
@GetMapping("/dashboard")
public DashboardResponse getDashboard() {
    return taskService.getDashboard();
}
@GetMapping("/search")
public List<Task> searchTasks(@RequestParam String keyword) {
    return taskService.searchTasks(keyword);
}
@GetMapping("/status")
public List<Task> getTasksByStatus(@RequestParam String status) {
    return taskService.getTasksByStatus(status);
}
@GetMapping("/priority")
public List<Task> getTasksByPriority(@RequestParam String priority) {
    return taskService.getTasksByPriority(priority);
}
@GetMapping("/overdue")
public List<Task> getOverdueTasks() {
    return taskService.getOverdueTasks();
}
@GetMapping("/sorted")
public List<Task> getTasksSortedByDueDate() {
    return taskService.getTasksSortedByDueDate();
}
@GetMapping("/sorted/priority")
public List<Task> getTasksSortedByPriority() {
    return taskService.getTasksSortedByPriority();
}
@PatchMapping("/{id}/complete")
public Task markTaskCompleted(@PathVariable Long id) {
    return taskService.markTaskCompleted(id);
}
}