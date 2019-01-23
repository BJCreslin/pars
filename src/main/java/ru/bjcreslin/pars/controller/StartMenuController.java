package ru.bjcreslin.pars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
@Controller
public class StartMenuController {
    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("program_name", StringController.PROGRAM_NAME);
        return "index";
    }
}
