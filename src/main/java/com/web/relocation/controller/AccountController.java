package com.web.relocation.controller;

import com.web.relocation.dto.AccountDto;
import com.web.relocation.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path = {"/account/"})
public class AccountController {

    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;

    @GetMapping("/login")
    public String viewLogin() {
        return "account/login";
    }

    @GetMapping("/login/result")
    @ResponseBody
    public Map<String, String> login(
            @RequestParam(value="error", defaultValue = "success", required = false) String error,
            @RequestParam(value = "exception", defaultValue = "", required = false) String exception
    ) {
        Map<String, String> result = new HashMap<>();
        result.put("result", error);
        result.put("msg", exception);

        return result;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<AccountDto> managerList(
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        List<AccountDto> managerList = accountService.getManagerList();

        return managerList;
    }
}
