package ru.itis.ediary.services;

import ru.itis.ediary.dto.parent.NewParentDto;
import ru.itis.ediary.dto.parent.ParentDto;
import ru.itis.ediary.dto.parent.ParentPage;

import java.util.UUID;

public interface ParentService {
    ParentPage getAllParents();
    ParentDto createParent(NewParentDto parent);
    ParentDto findById(UUID id);
    ParentDto updateById(ParentDto parent);
    void deleteById(UUID id);
}
