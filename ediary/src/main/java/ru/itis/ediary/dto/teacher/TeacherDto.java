package ru.itis.ediary.dto.teacher;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ru.itis.ediary.dto.timetable.TimetableDto;
import ru.itis.ediary.models.Teacher;
import ru.itis.ediary.models.Timetable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Double rating;
    private List<TimetableDto> timeTableList;

    public static TeacherDto from(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .age(teacher.getAge())
                .rating(teacher.getRating())
                .timeTableList(TimetableDto.from(teacher.getTimeTableList()))
                .build();
    }

    public static List<TeacherDto> from(List<Teacher> teachers) {
        if (CollectionUtils.isEmpty(teachers)) { return null; }

        return teachers
                .stream()
                .map(TeacherDto::from)
                .collect(Collectors.toList());
    }
}
