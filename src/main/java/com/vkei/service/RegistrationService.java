package com.vkei.service;

import com.vkei.dto.UserRegistrationDto;

public interface RegistrationService {

    Long registerUser(UserRegistrationDto dto);

}
