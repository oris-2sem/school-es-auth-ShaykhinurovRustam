package ru.itis.ediary.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.ediary.dto.ModelId;
import ru.itis.ediary.dto.teacher.NewTeacherDto;
import ru.itis.ediary.dto.teacher.TeacherDto;
import ru.itis.ediary.dto.teacher.TeacherPage;
import ru.itis.ediary.services.TeacherService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public TeacherPage getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/id")
    public TeacherDto findById(@RequestParam("id") UUID id) {
        return teacherService.findById(id);
    }

    @PostMapping("/create")
    public TeacherDto createTeacher(@RequestBody NewTeacherDto newTeacherDto) {
        return teacherService.createTeacher(newTeacherDto);
    }

    @PostMapping("/update")
    public TeacherDto updateById(@RequestBody TeacherDto teacherDto) {
        return teacherService.updateById(teacherDto);
    }

    @PostMapping("/del")
    public UUID deleteById(@RequestBody ModelId id) {
        teacherService.deleteById(id.getId());
        return id.getId();
    }

}
