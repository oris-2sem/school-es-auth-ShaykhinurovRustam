package ru.itis.ediary.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ru.itis.ediary.dto.student.StudentDto;
import ru.itis.ediary.dto.timetable.TimetableDto;
import ru.itis.ediary.models.Group;
import ru.itis.ediary.models.Student;
import ru.itis.ediary.models.Teacher;
import ru.itis.ediary.models.Timetable;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewGroupDto {
    private Integer course;
    private UUID teacherId;
    private Date dateInsert;
    private List<Student> students;
    private List<Timetable> timetables;

    public static NewGroupDto from(Group group) {
        return NewGroupDto.builder()
                .course(group.getCourse())
                .teacherId(group.getTeacher().getId())
                .students(group.getStudents())
                .timetables(group.getTimetables())
                .dateInsert(group.getDateInsert())
                .build();
    }

    public static List<NewGroupDto> from(List<Group> groups) {
        if (CollectionUtils.isEmpty(groups)) { return null; }

        return groups
                .stream()
                .map(NewGroupDto::from)
                .collect(Collectors.toList());
    }
}
