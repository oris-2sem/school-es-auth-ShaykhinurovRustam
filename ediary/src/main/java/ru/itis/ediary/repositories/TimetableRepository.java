package ru.itis.ediary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ediary.models.Timetable;

import java.util.UUID;

public interface TimetableRepository extends JpaRepository<Timetable, UUID> {
}
