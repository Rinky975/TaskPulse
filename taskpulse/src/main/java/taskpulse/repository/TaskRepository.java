package taskpulse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskpulse.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}