package taskpulse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskpulse.entity.Task;
import taskpulse.repository.TaskRepository;
import taskpulse.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {

        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }
}