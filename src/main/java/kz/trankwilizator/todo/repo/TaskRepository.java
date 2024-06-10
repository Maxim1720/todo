package kz.trankwilizator.todo.repo;

import kz.trankwilizator.todo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
