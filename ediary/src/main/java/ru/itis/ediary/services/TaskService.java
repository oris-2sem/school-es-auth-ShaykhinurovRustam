package ru.itis.ediary.services;

import ru.itis.ediary.dto.task.NewTaskDto;
import ru.itis.ediary.dto.task.TaskDto;
import ru.itis.ediary.dto.task.TaskPage;

import java.util.UUID;

public interface TaskService {
    TaskPage getAllTasks();
    TaskDto createTask(NewTaskDto task);
    TaskDto findById(UUID id);
    TaskDto updateById(TaskDto task);
    void deleteById(UUID id);
}
