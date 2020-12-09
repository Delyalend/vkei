package com.vkei.service;

import com.vkei.dto.SubjectRegistrationDto;
import com.vkei.exception.SubjectAlreadyExistsException;
import com.vkei.exception.SubjectIsAlreadyExistsInCollectionException;
import com.vkei.model.Subject;
import com.vkei.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepo;

    public SubjectServiceImpl(SubjectRepository subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    @Override
    @Transactional
    public void register(SubjectRegistrationDto subjectDto) {
        if (subjectRepo.findByTitle(subjectDto.getTitle()).isPresent()) {
            throw new SubjectAlreadyExistsException(subjectDto.getTitle());
        } else {
            Subject newSubject = Subject.builder()
                    .title(subjectDto.getTitle())
                    .build();
            subjectRepo.register(newSubject);
        }
    }

    @Override
    @Transactional
    public List<Subject> findAll() {
        return subjectRepo.findAll();
    }

    @Override
    @Transactional
    public void addEasySubjectToUser(Long subjectId, Long userId) throws SubjectIsAlreadyExistsInCollectionException {
        subjectRepo.addEasySubjectToUser(subjectId, userId);
    }

    @Override
    @Transactional
    public void addHardSubjectToUser(Long subjectId, Long userId) throws SubjectIsAlreadyExistsInCollectionException {
        subjectRepo.addHardSubjectToUser(subjectId, userId);
    }


}
