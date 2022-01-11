package com.web.relocation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(path = {"/", "/home"})
    public String home (Model model) {
        model.addAttribute("testStR",  "이제");
        return "index";
    }
}
