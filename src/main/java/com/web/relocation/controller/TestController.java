package com.web.relocation.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.web.relocation.entity.MenuEntity;
import com.web.relocation.service.MenuTestService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Getter
    @Setter
    public class StudentInfo {
        public int id;
        public String name;
        public int age;

        StudentInfo(int id, String name, int age) {
            setId(id);
            setName(name);
            setAge(age);
        }
    }

    @GetMapping(path = {"/home"})
    public String home (Model model) {

        List<StudentInfo> students = new ArrayList<>();

        students.add(new StudentInfo(1, "홍길동", 13));
        students.add(new StudentInfo(2, "홍길순", 17));
        students.add(new StudentInfo(3, "고길동", 32));
        students.add(new StudentInfo(4, "둘리", 11));

        model.addAttribute("testStR",  "이제");
        model.addAttribute("students",  students);

        return "home";
    }

    @ResponseBody
    @RequestMapping(path = {"/test"})
    public String test() throws Exception {
        logger.info(String.format("테스트 계정 정보 \n아이디 : tester12\n비밀번호 : %s", "!Love1234356"));
                //Hashing.sha256().hashString("!Love1234356".toString(), Charsets.UTF_8).toString()));
        /*logger.trace("Trace Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("Warn Level 테스트");
        logger.error("ERROR Level 테스트");*/


        return "test";
    }

    @Autowired
    MenuTestService menuTestService;

    @ResponseBody
    @GetMapping(path = {"/menu_test"})
    public List<MenuEntity> menuTest() {
        return menuTestService.menuListTest();
    }
}
