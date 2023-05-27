package ru.itis.ediary.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.ediary.dto.ModelId;
import ru.itis.ediary.dto.task.NewTaskDto;
import ru.itis.ediary.dto.task.TaskDto;
import ru.itis.ediary.dto.task.TaskPage;
import ru.itis.ediary.services.TaskService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public TaskPage getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/id")
    public TaskDto findById(@RequestParam("id") UUID id) {
        return taskService.findById(id);
    }

    @PostMapping("/create")
    public TaskDto createTask(@RequestBody NewTaskDto newTaskDto) {
        return taskService.createTask(newTaskDto);
    }

    @PostMapping("/update")
    public TaskDto updateById(@RequestBody TaskDto taskDto) {
        return taskService.updateById(taskDto);
    }

    @PostMapping("/del")
    public UUID deleteById(@RequestBody ModelId id) {
        taskService.deleteById(id.getId());
        return id.getId();
    }
}
