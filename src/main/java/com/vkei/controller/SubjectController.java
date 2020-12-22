package com.vkei.controller;

import com.vkei.dto.SubjectRegistrationDto;
import com.vkei.exception.SubjectIsAlreadyExistsInCollectionException;
import com.vkei.model.Subject;
import com.vkei.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SubjectController {

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(path = "subjects/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerSubject(@Valid @RequestBody SubjectRegistrationDto subjectDto) {
        subjectService.register(subjectDto);
    }

    @GetMapping(path = "subjects/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subject> getSubjects() {
        return subjectService.findAll();
    }


    @PostMapping(path = "subjects/easy/{subjectId}/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addEasySubjectToUser(@PathVariable Long subjectId, @PathVariable Long userId) throws SubjectIsAlreadyExistsInCollectionException {
        subjectService.addEasySubjectToUser(subjectId, userId);
    }

    @PostMapping(path = "subjects/hard/{subjectId}/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addHardSubjectToUser(@PathVariable Long subjectId, @PathVariable Long userId) throws SubjectIsAlreadyExistsInCollectionException {
        subjectService.addHardSubjectToUser(subjectId, userId);
    }



}
