package ru.bjcreslin.pars.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static ru.bjcreslin.pars.controller.StringConroller.PROGRAM_NAME;

@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/form")
    public String formload(Model model) {
        model.addAttribute("program_name", PROGRAM_NAME);
        return "upload";
    }
}
