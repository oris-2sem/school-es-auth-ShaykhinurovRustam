package ru.itis.ediary.dto.task;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ru.itis.ediary.models.Lesson;
import ru.itis.ediary.models.Student;
import ru.itis.ediary.models.Task;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTaskDto {
    private UUID lessonId;
    private UUID studentId;
    private String commentary;
    private String description;
    private String mark;

    public static NewTaskDto from(Task task) {
        return NewTaskDto.builder()
                .lessonId(task.getLesson().getId())
                .studentId(task.getStudent().getId())
                .commentary(task.getCommentary())
                .description(task.getDescription())
                .mark(task.getMark())
                .build();
    }

    public static Set<NewTaskDto> from(List<Task> tasks) {
        if (CollectionUtils.isEmpty(tasks)) { return null; }

        return tasks
                .stream()
                .map(NewTaskDto::from)
                .collect(Collectors.toSet());
    }
}
