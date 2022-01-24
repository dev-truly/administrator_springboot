package com.web.relocation.service;

import com.web.relocation.dto.user.UserDto;
import com.web.relocation.dto.user.UserSearchDto;
import com.web.relocation.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDto> getUserList(UserSearchDto param, Pageable pageable);
}
