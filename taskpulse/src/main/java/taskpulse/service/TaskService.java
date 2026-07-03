package taskpulse.service;

import taskpulse.entity.Task;

import java.util.List;
import taskpulse.dto.DashboardResponse;
public interface TaskService {

    Task createTask(Task task);

    List<Task> getMyTasks(); 
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    DashboardResponse getDashboard();
    List<Task> searchTasks(String keyword);
    List<Task> getTasksByStatus(String status);
    List<Task> getTasksByPriority(String priority);
    List<Task> getOverdueTasks();
    List<Task> getTasksSortedByDueDate();
    List<Task> getTasksSortedByPriority();
    Task markTaskCompleted(Long id);
}