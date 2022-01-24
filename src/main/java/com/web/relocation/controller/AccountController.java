package com.web.relocation.controller;

import com.web.relocation.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
}
