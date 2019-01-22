package ru.bjcreslin.pars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.bjcreslin.pars.Service.ProductServiceImpl;

import static ru.bjcreslin.pars.controller.StringConroller.PROGRAM_NAME;

@Controller
@RequestMapping("/getall")
public class cntrl {

    @Autowired
    ProductServiceImpl productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String action(Model model) {
        model.addAttribute("table", productService.getAll());
        model.addAttribute("program_name", PROGRAM_NAME);
        return "index";
    }
}
