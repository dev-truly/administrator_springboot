package com.web.relocation.repository;

import com.web.relocation.dto.user.UserDto;
import com.web.relocation.dto.user.UserSearchDto;
import com.web.relocation.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository {
    public Page<UserDto> search(UserSearchDto params, Pageable pageable);
}
