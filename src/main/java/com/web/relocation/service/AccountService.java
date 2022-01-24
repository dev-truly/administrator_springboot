package com.web.relocation.service;

import com.web.relocation.entity.UserEntity;

public interface AccountService{
    UserEntity findByUserIdAndPassword(String userId, String password);

    void registerUser(UserEntity user);
}
