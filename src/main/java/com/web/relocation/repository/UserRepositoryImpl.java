package com.web.relocation.repository;

import com.querydsl.jpa.JPQLQuery;
import com.web.relocation.common.FlagType;
import com.web.relocation.dto.user.UserDto;
import com.web.relocation.dto.user.UserSearchDto;
import com.web.relocation.entity.QUserEntity;
import com.web.relocation.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepository {
    @Autowired
    ModelMapper modelMapper;

    public UserRepositoryImpl() {
        super(UserEntity.class);
    }
    private QUserEntity user = QUserEntity.userEntity;
    @Override
    public Page<UserDto> search(UserSearchDto params, Pageable pageable) {
        JPQLQuery<UserEntity> query = from(user);
        query.where(user.delete.eq(FlagType.n));

        if (params.getUserName() != null) {
            query.where(user.userName.like(params.getUserName()));
        }

        List<UserEntity> userList = getQuerydsl().applyPagination(pageable, query).fetch();
        List<UserDto> users = userList.stream().map(p -> modelMapper.map(p, UserDto.class)).collect(Collectors.toList());

        long tatalCount = query.fetchCount();

        return new PageImpl<UserDto>(users, pageable, tatalCount);
    }
}
