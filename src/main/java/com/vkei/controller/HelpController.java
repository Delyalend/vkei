package com.vkei.controller;

import com.vkei.model.User;
import com.vkei.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelpController {

    private HelpService helpService;

    @Autowired
    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }


    //Кто может помочь мне
    @GetMapping(path = "help/me/{subjectId}")
    public List<User> getUsersCanHelpedMe(@PathVariable Long subjectId) {
        return helpService.findUsersCanHelpMeBySubject(subjectId);
    }


    //Кому могу помочь я
    @GetMapping(path = "help/i/{subjectId}")
    public List<User> getUserCanHelpedI(@PathVariable Long subjectId) {
        return helpService.findUsersCanHelpIBySubject(subjectId);
    }

    //Кому могу помочь я и кто может помочь мне
    @GetMapping(path = "help/i/me/{subscribeId}")
    public List<User> getUserCanHelpedIMe(@PathVariable Long subscribeId){
        return helpService.findUsersCanHelpEachOther(subscribeId);
    }

}
