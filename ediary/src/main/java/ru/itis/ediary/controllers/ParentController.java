package ru.itis.ediary.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.ediary.dto.ModelId;
import ru.itis.ediary.dto.parent.NewParentDto;
import ru.itis.ediary.dto.parent.ParentDto;
import ru.itis.ediary.dto.parent.ParentPage;
import ru.itis.ediary.services.ParentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parent")
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    public ParentPage getAllParents() {
        return parentService.getAllParents();
    }

    @GetMapping("/id")
    public ParentDto findById(@RequestParam("id") UUID id) {
        return parentService.findById(id);
    }

    @PostMapping("/create")
    public ParentDto createParent(@RequestBody NewParentDto newParentDto) {
        return parentService.createParent(newParentDto);
    }

    @PostMapping("/update")
    public ParentDto updateById(@RequestBody ParentDto parentDto) {
        return parentService.updateById(parentDto);
    }

    @PostMapping("/del")
    public UUID deleteById(@RequestBody ModelId id) {
        parentService.deleteById(id.getId());
        return id.getId();
    }
}
