package ru.itis.ediary.services;

import ru.itis.ediary.dto.lesson.LessonDto;
import ru.itis.ediary.dto.lesson.LessonPage;
import ru.itis.ediary.dto.lesson.NewLessonDto;

import java.util.UUID;

public interface LessonService {
    LessonPage getAllLessons();
    LessonDto createLesson(NewLessonDto lesson);
    LessonDto findById(UUID id);
    LessonDto updateById(LessonDto lesson);
    void deleteById(UUID id);
}
