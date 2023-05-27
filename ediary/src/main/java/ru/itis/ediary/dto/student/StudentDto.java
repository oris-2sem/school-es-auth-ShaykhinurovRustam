package ru.itis.ediary.dto.student;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ru.itis.ediary.dto.task.TaskDto;
import ru.itis.ediary.models.Group;
import ru.itis.ediary.models.Parent;
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
public class StudentDto {
    private UUID id;
    private UUID parentId;
    private UUID groupId;
    private String firstName;
    private Double rating;
    private List<TaskDto> taskList;

    public static StudentDto from(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .rating(student.getRating())
                .parentId(student.getParent().getId())
                .groupId(student.getGroup().getId())
                .taskList(TaskDto.from(student.getTaskList()))
                .build();
    }

    public static List<StudentDto> from(List<Student> students) {
        if (CollectionUtils.isEmpty(students)) { return null; }

        return students
                .stream()
                .map(StudentDto::from)
                .collect(Collectors.toList());
    }
}
