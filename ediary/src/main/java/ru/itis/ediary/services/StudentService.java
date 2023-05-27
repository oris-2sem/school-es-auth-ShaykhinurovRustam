package ru.itis.ediary.services;

import ru.itis.ediary.dto.student.NewStudentDto;
import ru.itis.ediary.dto.student.StudentDto;
import ru.itis.ediary.dto.student.StudentPage;

import java.util.UUID;

public interface StudentService {
    StudentPage getAllStudents();
    StudentDto createStudent(NewStudentDto student);
    StudentDto findById(UUID id);
    StudentDto updateById(StudentDto student);
    void deleteById(UUID id);
}
