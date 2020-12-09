package com.vkei.dto;

import com.vkei.model.Subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestDto {
    private List<Subject> subjectEasy;
    private List<Subject> subjectHard;

    public TestDto(Collection<Subject> subjectEasy, Collection<Subject> subjectHard){
        this.subjectEasy = new ArrayList<>(subjectEasy);
        this.subjectHard = new ArrayList<>(subjectHard);
    }

    public List<Subject> getSubjectEasy() {
        return subjectEasy;
    }

    public List<Subject> getSubjectHard() {
        return subjectHard;
    }
}
