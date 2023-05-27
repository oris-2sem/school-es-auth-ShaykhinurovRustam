package ru.itis.ediary.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.ediary.dto.timetable.NewTimetableDto;
import ru.itis.ediary.dto.timetable.TimetableDto;
import ru.itis.ediary.dto.timetable.TimetablePage;
import ru.itis.ediary.models.Group;
import ru.itis.ediary.models.Teacher;
import ru.itis.ediary.models.Timetable;
import ru.itis.ediary.repositories.GroupRepository;
import ru.itis.ediary.repositories.TeacherRepository;
import ru.itis.ediary.repositories.TimetableRepository;
import ru.itis.ediary.services.TimetableService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.ediary.dto.timetable.TimetableDto.from;

@RequiredArgsConstructor
@Service
public class TimetableServiceImpl implements TimetableService {
    private final TimetableRepository timetableRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public TimetablePage getAllTimetables() {
        List<Timetable> timetables = timetableRepository.findAll();
        return TimetablePage.builder()
                .timetableList(TimetableDto.from(timetables))
                .build();
    }

    @Override
    public TimetableDto createTimetable(NewTimetableDto newTimetable) {
        Optional<Group> group = groupRepository.findById(newTimetable.getGroupId());
        Optional<Teacher> teacher = teacherRepository.findById(newTimetable.getTeacherId());

        Timetable timetable = Timetable.builder()
                .dateTime(newTimetable.getDateTime())
                .group(group.get())
                .teacher(teacher.get())
                .room(newTimetable.getRoom())
                .build();

        timetableRepository.save(timetable);
        return from(timetable);
    }

    @Override
    public TimetableDto findById(UUID id) {
        Optional<Timetable> timetable = timetableRepository.findById(id);
        return timetable.map(TimetableDto::from).orElse(null);
    }

    @Override
    public TimetableDto updateById(TimetableDto timetable) {
        Optional<Timetable> optionalTimetable = timetableRepository.findById(timetable.getId());

        if (optionalTimetable.isPresent()) {
            Timetable timetableToUpdate = optionalTimetable.get();
            timetableToUpdate.setRoom(timetable.getRoom());
            timetableToUpdate.setDateTime(timetable.getDateTime());

            timetableRepository.save(timetableToUpdate);
            return from(timetableToUpdate);
        } else {
            throw new EntityNotFoundException("Entity with id " + timetable.getId() + " not found");
        }
    }

    @Override
    public void deleteById(UUID id) {
        timetableRepository.deleteById(id);
    }
}
