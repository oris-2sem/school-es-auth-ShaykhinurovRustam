package ru.itis.ediary.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.ediary.dto.ModelId;
import ru.itis.ediary.dto.student.NewStudentDto;
import ru.itis.ediary.dto.student.StudentDto;
import ru.itis.ediary.dto.student.StudentPage;
import ru.itis.ediary.services.StudentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public StudentPage getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/id")
    public StudentDto findById(@RequestParam("id") UUID id) {
        return studentService.findById(id);
    }

    @PostMapping("/create")
    public StudentDto createStudent(@RequestBody NewStudentDto newStudentDto) {
        return studentService.createStudent(newStudentDto);
    }

    @PostMapping("/update")
    public StudentDto updateById(@RequestBody StudentDto studentDto) {
        return studentService.updateById(studentDto);
    }

    @PostMapping("/del")
    public UUID deleteById(@RequestBody ModelId id) {
        studentService.deleteById(id.getId());
        return id.getId();
    }

}
