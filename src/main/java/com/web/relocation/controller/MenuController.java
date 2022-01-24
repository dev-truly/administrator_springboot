package com.web.relocation.controller;

import com.web.relocation.dto.account.LoginDto;
import com.web.relocation.dto.manu.SelectMenuDto;
import com.web.relocation.service.MenuService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = {"/admin/menu/"})
public class MenuController {
    @Autowired
    @Qualifier("menuService")
    MenuService menuService;

    @ResponseBody
    @GetMapping(path = {"/list"})
    public List<SelectMenuDto> viewMenuList(Authentication authentication) throws ParseException {
        LoginDto userDetails = (LoginDto) authentication.getPrincipal();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(userDetails.getMenuCode());
        List<SelectMenuDto> menu = menuService.findMenuList(obj);
        return menu;
    }
}
