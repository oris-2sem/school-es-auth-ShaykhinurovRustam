package ru.itis.ediary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ediary.models.Task;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
