package com.vkei.service;

import com.vkei.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class HelpServiceImpl implements HelpService {


    private EntityManager em;
    private UserService userService;

    @Autowired
    public HelpServiceImpl(EntityManager em, UserService userService) {
        this.em = em;
        this.userService = userService;
    }

    @Override
    @Transactional
    public List<User> findUsersCanHelpMeBySubject(Long subjectId) {
        List<User> usersCanHelp = new ArrayList<>();
        List<User> all = userService.findAll();
        all.forEach(userTemp ->
                userTemp.getSubjectEasy().forEach(subject -> {
                    if(subject.getId().equals(subjectId)) {
                        usersCanHelp.add(userTemp);
                    }
                }));
        return usersCanHelp;
    }

    @Override
    @Transactional
    public List<User> findUsersCanHelpIBySubject(Long subjectId) {
        List<User> usersNeededHelp = new ArrayList<>();
        List<User> all = userService.findAll();
        all.forEach(userTemp ->
                userTemp.getSubjectHard().forEach(subject -> {
                    if(subject.getId().equals(subjectId)) {
                        usersNeededHelp.add(userTemp);
                    }
                }));
        return usersNeededHelp;
    }

    //TODO:найти тех юзеров, которым могу помочь я и которые могут помочь мне
    @Override
    @Transactional
    public List<User> findUsersCanHelpEachOther(Long subjectId) {

        return null;
    }
}
