package ru.itis.ediary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ediary.models.Lesson;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
}
