package com.vkei.service;

import com.vkei.model.User;

import java.util.List;

public interface HelpService {
    List<User> findUsersCanHelpMeBySubject(Long subjectId);
    List<User> findUsersCanHelpIBySubject(Long subjectId);
    List<User> findUsersCanHelpEachOther(Long userId);
}
