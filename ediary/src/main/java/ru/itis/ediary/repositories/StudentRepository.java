package ru.itis.ediary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ediary.models.Student;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
}
