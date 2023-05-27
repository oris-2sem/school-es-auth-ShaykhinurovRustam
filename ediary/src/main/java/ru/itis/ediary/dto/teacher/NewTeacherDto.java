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
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTeacherDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Double rating;
    private List<Timetable> timeTableList;

    public static NewTeacherDto from(Teacher teacher) {
        return NewTeacherDto.builder()
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .age(teacher.getAge())
                .rating(teacher.getRating())
                .timeTableList(teacher.getTimeTableList())
                .build();
    }

    public static List<NewTeacherDto> from(List<Teacher> teachers) {
        if (CollectionUtils.isEmpty(teachers)) { return null; }

        return teachers
                .stream()
                .map(NewTeacherDto::from)
                .collect(Collectors.toList());
    }
}
