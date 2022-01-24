package com.web.relocation.service;

import com.web.relocation.dto.user.UserDto;
import com.web.relocation.dto.user.UserSearchDto;
import com.web.relocation.entity.UserEntity;
import com.web.relocation.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<UserDto> getUserList(UserSearchDto param, Pageable pageable) {
        return accountRepository.search(param, pageable);
        /*List<UserEntity> managerData = null;

        GenericSpecification<UserEntity> specs = new GenericSpecification<>();

        Specification<UserEntity> spec = Specification.where(
                specs.equal("delete", FlagType.n)
        );

        Sort sort = Sort.by("userNo").ascending();

        managerData = accountRepository.findAll(spec, sort);

        List<UserDto> managerList = managerData.stream().map(p -> modelMapper.map(p, UserDto.class)).collect(Collectors.toList());

        return managerList;*/
    }
}
