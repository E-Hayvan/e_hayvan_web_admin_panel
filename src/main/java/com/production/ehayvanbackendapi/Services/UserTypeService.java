package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.Entities.UserType;
import com.production.ehayvanbackendapi.Repositories.UserTypeRepository;

import java.util.List;

public class UserTypeService {
    private final UserTypeRepository userTypeRepository;

    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }
    public List<UserType> getAllUserTypes(){
        return userTypeRepository.findAll();
    }
}
