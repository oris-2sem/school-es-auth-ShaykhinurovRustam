package ru.itis.ediary.dto.lesson;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ru.itis.ediary.dto.task.TaskDto;
import ru.itis.ediary.models.Lesson;
import ru.itis.ediary.models.Task;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewLessonDto {
    private UUID timeTableId;
    private String theme;
    private String subject;
    private List<Task> taskList;

    public static NewLessonDto from(Lesson lesson) {
        return NewLessonDto.builder()
                .theme(lesson.getTheme())
                .subject(lesson.getSubject())
                .timeTableId(lesson.getTimeTable().getId())
                .taskList(lesson.getTaskList())
                .build();
    }

    public static List<NewLessonDto> from(List<Lesson> lessons) {
        if (CollectionUtils.isEmpty(lessons)) { return null; }

        return lessons
                .stream()
                .map(NewLessonDto::from)
                .collect(Collectors.toList());
    }
}
