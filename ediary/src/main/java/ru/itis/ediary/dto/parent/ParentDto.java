package ru.itis.ediary.dto.parent;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import ru.itis.ediary.dto.student.StudentDto;
import ru.itis.ediary.models.Parent;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ru.itis.ediary.dto.student.StudentDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentDto {
    private UUID id;
    private String name;
    private List<StudentDto> children;

    public static ParentDto from(Parent parent) {
        return ParentDto.builder()
                .id(parent.getId())
                .name(parent.getName())
                .children(StudentDto.from(parent.getChildren()))
                .build();
    }

    public static List<ParentDto> from(List<Parent> parents) {
        if (CollectionUtils.isEmpty(parents)) { return null; }

        return parents
                .stream()
                .map(ParentDto::from)
                .collect(Collectors.toList());
    }

}
