package taskpulse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskpulse.entity.Task;
import taskpulse.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

    long countByUser(User user);

    long countByUserAndStatus(User user, String status);
    List<Task> findByUserAndTitleContainingIgnoreCase(User user, String keyword);
    List<Task> findByUserAndStatus(User user, String status);
List<Task> findByUserAndPriority(User user, String priority);
List<Task> findByUserAndDueDateBefore(User user, LocalDateTime dateTime);
List<Task> findByUserOrderByDueDateAsc(User user);
List<Task> findByUserOrderByPriorityAsc(User user);    
    

}