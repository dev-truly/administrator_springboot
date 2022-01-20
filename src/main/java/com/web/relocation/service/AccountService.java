package com.web.relocation.service;

import com.web.relocation.dto.AccountDto;
import com.web.relocation.entity.UserEntity;

import java.util.List;

public interface AccountService{
    UserEntity findByUserIdAndPassword(String userId, String password);

    void registerUser(UserEntity user);

    List<AccountDto> getManagerList();
}
