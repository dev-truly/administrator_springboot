package com.web.relocation.service;

import com.web.relocation.common.FlagType;
import com.web.relocation.common.GenericSpecification;
import com.web.relocation.common.Role;
import com.web.relocation.common.Util;
import com.web.relocation.dto.AccountDto;
import com.web.relocation.dto.LoginDto;
import com.web.relocation.entity.UserEntity;
import com.web.relocation.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("accountService")
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Qualifier(value="accountRepository")
    private AccountRepository accountRepository;


    @Override
    public UserEntity findByUserIdAndPassword(String userId, String password) {
        password = Util.getHashedString(password, "SHA-256");
        UserEntity user = accountRepository.findByUserIdAndPassword(userId, password);

        //accountRepository.
        return user;
    }

    @Override
    public void registerUser(UserEntity user) {
        String password = user.getPassword();
        password = Util.getHashedString(password, "SHA-256");
        user.setPassword(password);
        //accountRepository.save(member);
    }
/*

    @Transactional
    public int joinUser(UserEntity user) {

    }
*/

    @Override
    @Transactional(readOnly = true) // DB에서 읽기 전용으로 설정 (insert,update,delete예외발생)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = accountRepository.findByUserId(userId);

        userEntityOptional
                .orElseThrow(() -> new UsernameNotFoundException(""));

        UserEntity user = userEntityOptional.get();
        LoginDto loginDto = modelMapper.map(user, LoginDto.class);
        loginDto.setAuth(Role.ADMIN.getValue());

        return loginDto;
    }

    @Override
    public List<AccountDto> getManagerList() {
        List<UserEntity> managerData = null;

        GenericSpecification<UserEntity> specs = new GenericSpecification<>();

        Specification<UserEntity> spec = Specification.where(
                specs.equal("delete", FlagType.n)
        );

        Sort sort = Sort.by("userNo").ascending();

        managerData = accountRepository.findAll(spec, sort);

        List<AccountDto> managerList = managerData.stream().map(p -> modelMapper.map(p, AccountDto.class)).collect(Collectors.toList());

        return managerList;
    }
}
