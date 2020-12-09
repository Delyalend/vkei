package com.vkei.service;

import com.vkei.dto.SubjectRegistrationDto;
import com.vkei.exception.SubjectIsAlreadyExistsInCollectionException;
import com.vkei.model.Subject;

import java.util.List;

public interface SubjectService {
    void register(SubjectRegistrationDto subjectDto);
    List<Subject> findAll();
    void addEasySubjectToUser(Long subjectId, Long userId) throws SubjectIsAlreadyExistsInCollectionException;
    void addHardSubjectToUser(Long subjectId, Long userId) throws SubjectIsAlreadyExistsInCollectionException;
}
