package com.web.relocation.controller;

import com.web.relocation.dto.user.UserDto;
import com.web.relocation.dto.user.UserSearchDto;
import com.web.relocation.entity.UserEntity;
import com.web.relocation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping(path = {"/admin/user/"})
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list")
    @ResponseBody
    public Page<UserDto> userList(
            UserSearchDto param,
            Pageable pageable
    ) {
        Page<UserDto> userList = userService.getUserList(param, pageable);

        return userList;
    }
}
