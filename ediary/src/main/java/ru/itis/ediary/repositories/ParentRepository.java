package ru.itis.ediary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ediary.models.Parent;

import java.util.UUID;

public interface ParentRepository extends JpaRepository<Parent, UUID> {
}
