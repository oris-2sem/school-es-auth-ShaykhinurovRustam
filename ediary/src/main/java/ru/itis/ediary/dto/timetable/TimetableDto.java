package ru.itis.ediary.dto.timetable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ru.itis.ediary.models.Timetable;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimetableDto {
    private UUID id;
    private UUID teacherId;
    private UUID groupId;
    private Date dateTime;
    private String room;

    public static TimetableDto from(Timetable timetable) {
        return TimetableDto.builder()
                .id(timetable.getId())
                .dateTime(timetable.getDateTime())
                .room(timetable.getRoom())
                .teacherId(timetable.getTeacher().getId())
                .groupId(timetable.getGroup().getId())
                .build();
    }

    public static List<TimetableDto> from(List<Timetable> timetables) {
        if (CollectionUtils.isEmpty(timetables)) { return null; }

        return timetables
                .stream()
                .map(TimetableDto::from)
                .collect(Collectors.toList());
    }
}
