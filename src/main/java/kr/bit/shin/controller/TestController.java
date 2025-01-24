package kr.bit.shin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @GetMapping("/list")
    public String list(Model model){
        List<String> list = new ArrayList<>();
        list.add("자바");
        list.add("스프링");
        list.add("부트");

        model.addAttribute("list",list);
        return "list";
    }
}
