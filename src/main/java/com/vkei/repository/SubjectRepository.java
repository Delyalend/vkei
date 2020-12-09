package com.vkei.repository;

import com.vkei.exception.SubjectIsAlreadyExistsInCollectionException;
import com.vkei.model.Subject;
import com.vkei.model.User;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository {
    Long register(Subject subject);
    Optional<Subject> findByTitle(String title);
    List<Subject> findAll();
    void addEasySubjectToUser(Long subjectId, Long userId) throws SubjectIsAlreadyExistsInCollectionException;
    void addHardSubjectToUser(Long subjectId, Long userId) throws SubjectIsAlreadyExistsInCollectionException;
}
