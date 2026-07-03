package taskpulse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import taskpulse.entity.Task;
import taskpulse.entity.User;
import taskpulse.repository.TaskRepository;
import taskpulse.repository.UserRepository;
import taskpulse.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import taskpulse.dto.DashboardResponse;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Task createTask(Task task) {

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());

        if (task.getStatus() == null) {
            task.setStatus("Pending");
        }

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getMyTasks() {

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByUser(user);
    }
    @Override
public Task updateTask(Long id, Task updatedTask) {

    UserDetails userDetails =
            (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

    String email = userDetails.getUsername();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Task task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));

    if (!task.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("Unauthorized");
    }

    task.setTitle(updatedTask.getTitle());
    task.setDescription(updatedTask.getDescription());
    task.setPriority(updatedTask.getPriority());
    task.setStatus(updatedTask.getStatus());
    task.setDueDate(updatedTask.getDueDate());

    return taskRepository.save(task);
}
@Override
public void deleteTask(Long id) {

    UserDetails userDetails =
            (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

    String email = userDetails.getUsername();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Task task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));

    if (!task.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("Unauthorized");
    }

    taskRepository.delete(task);
}
@Override
public DashboardResponse getDashboard() {

    UserDetails userDetails =
            (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

    String email = userDetails.getUsername();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    long total = taskRepository.countByUser(user);
    long completed = taskRepository.countByUserAndStatus(user, "Completed");
    long pending = taskRepository.countByUserAndStatus(user, "Pending");
    long inProgress = taskRepository.countByUserAndStatus(user, "In Progress");

    return new DashboardResponse(
            total,
            completed,
            pending,
            inProgress
    );
}
@Override
public List<Task> searchTasks(String keyword) {

    UserDetails userDetails =
            (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

    String email = userDetails.getUsername();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return taskRepository.findByUserAndTitleContainingIgnoreCase(user, keyword);
}
@Override
public List<Task> getTasksByStatus(String status) {

    UserDetails userDetails =
            (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

    String email = userDetails.getUsername();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return taskRepository.findByUserAndStatus(user, status);
}
@Override
public List<Task> getTasksByPriority(String priority) {

    UserDetails userDetails =
            (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

    String email = userDetails.getUsername();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return taskRepository.findByUserAndPriority(user, priority);
}
@Override
public List<Task> getOverdueTasks() {

    UserDetails userDetails =
            (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

    String email = userDetails.getUsername();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return taskRepository.findByUserAndDueDateBefore(
            user,
            LocalDateTime.now()
    );
}
@Override
public List<Task> getTasksSortedByDueDate() {

    UserDetails userDetails =
            (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

    String email = userDetails.getUsername();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return taskRepository.findByUserOrderByDueDateAsc(user);
}
@Override
public List<Task> getTasksSortedByPriority() {

    UserDetails userDetails =
            (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

    String email = userDetails.getUsername();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return taskRepository.findByUserOrderByPriorityAsc(user);
}
@Override
public Task markTaskCompleted(Long id) {

    UserDetails userDetails =
            (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

    User user = userRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    Task task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));

    if (!task.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("Unauthorized");
    }

    task.setStatus("Completed");

    return taskRepository.save(task);
}
}