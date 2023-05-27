package ru.itis.ediary.services;

import ru.itis.ediary.dto.teacher.NewTeacherDto;
import ru.itis.ediary.dto.teacher.TeacherDto;
import ru.itis.ediary.dto.teacher.TeacherPage;

import java.util.UUID;

public interface TeacherService {
    TeacherPage getAllTeachers();
    TeacherDto createTeacher(NewTeacherDto teacher);
    TeacherDto findById(UUID id);
    TeacherDto updateById(TeacherDto teacher);
    void deleteById(UUID id);
}
