package ru.itis.ediary.services;

import ru.itis.ediary.dto.group.GroupDto;
import ru.itis.ediary.dto.group.GroupPage;
import ru.itis.ediary.dto.group.NewGroupDto;

import java.util.UUID;

public interface GroupService {
    GroupPage getAllGroups();
    GroupDto createGroup(NewGroupDto group);
    GroupDto findById(UUID id);
    GroupDto updateById(GroupDto group);
    void deleteById(UUID id);
}
