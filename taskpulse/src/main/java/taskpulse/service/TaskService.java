package taskpulse.service;

import taskpulse.entity.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    List<Task> getAllTasks();

}