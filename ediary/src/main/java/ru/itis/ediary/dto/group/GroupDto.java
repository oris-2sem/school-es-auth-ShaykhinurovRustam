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

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDto {
    private UUID id;
    private UUID teacherId;
    private Integer course;
    private List<StudentDto> students;
    private List<TimetableDto> timetables;

    public static GroupDto from(Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .course(group.getCourse())
                .teacherId(group.getTeacher().getId())
                .students(StudentDto.from(group.getStudents()))
                .timetables(TimetableDto.from(group.getTimetables()))
                .build();
    }

    public static List<GroupDto> from(List<Group> groups) {
        if (CollectionUtils.isEmpty(groups)) { return null; }
        
        return groups
                .stream()
                .map(GroupDto::from)
                .collect(Collectors.toList());
    }
}
