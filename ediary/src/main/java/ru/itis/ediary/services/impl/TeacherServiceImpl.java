package ru.itis.ediary.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.ediary.dto.teacher.NewTeacherDto;
import ru.itis.ediary.dto.teacher.TeacherDto;
import ru.itis.ediary.dto.teacher.TeacherPage;
import ru.itis.ediary.models.Teacher;
import ru.itis.ediary.repositories.TeacherRepository;
import ru.itis.ediary.services.TeacherService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.ediary.dto.teacher.TeacherDto.from;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public TeacherPage getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();

        return TeacherPage.builder()
                .teacherList(from(teachers))
                .build();
    }

    @Override
    public TeacherDto createTeacher(NewTeacherDto newTeacher) {
        Teacher teacher = Teacher.builder()
                .age(newTeacher.getAge())
                .firstName(newTeacher.getFirstName())
                .lastName(newTeacher.getLastName())
                .rating(newTeacher.getRating())
                .timeTableList(newTeacher.getTimeTableList())
                .build();

        teacherRepository.save(teacher);
        return from(teacher);
    }

    @Override
    public TeacherDto findById(UUID id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.map(TeacherDto::from).orElse(null);
    }

    @Override
    public TeacherDto updateById(TeacherDto teacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacher.getId());

        if (optionalTeacher.isPresent()) {
            Teacher teacherToUpdate = optionalTeacher.get();

            if (teacher.getAge() != null) {
                teacherToUpdate.setAge(teacher.getAge());
            }

            if (teacher.getRating() != null) {
                teacherToUpdate.setRating(teacher.getRating());
            }

            if (teacher.getFirstName() != null) {
                teacherToUpdate.setFirstName(teacher.getFirstName());
            }

            if (teacher.getLastName() != null) {
                teacherToUpdate.setLastName(teacher.getLastName());
            }

            teacherRepository.save(teacherToUpdate);
            return from(teacherToUpdate);
        } else {
            throw new EntityNotFoundException("Entity with id " + teacher.getId() + " not found");
        }
    }

    @Override
    public void deleteById(UUID id) {
        teacherRepository.deleteById(id);
    }
}
