package ru.itis.ediary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.ediary.models.Group;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
}
