package ru.bjcreslin.pars.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.pars.Service.URLGroupeServiceImpl;
import ru.bjcreslin.pars.model.UrlGroup;
import ru.bjcreslin.pars.repository.UrlGroupeRepository;

@Slf4j
@Controller
@RequestMapping("/groupe")
public class Groupe {

    @Autowired
    URLGroupeServiceImpl urlGroupeRepository;

    @RequestMapping("/init")
    public String initGroupe(Model model) {
        String groupe;
        String url;
        UrlGroup urlGroup;

        groupe = "насосы";
        url = "https://stroypark.su/catalog/injenernyie-sistemyi-i-oborudovanie/sistemyi-vodosnabjeniya-i-kanalizatsii/nasosnoe-oborudovanie";

        urlGroup = new UrlGroup(groupe, url);
        urlGroupeRepository.save(urlGroup);


        groupe = "расширительные баки";
        url = "https://stroypark.su/catalog/injenernyie-sistemyi-i-oborudovanie/sistemyi-vodosnabjeniya-i-kanalizatsii/baki-rasshiritelnyie";

        urlGroup = new UrlGroup(groupe, url);
        urlGroupeRepository.save(urlGroup);


        groupe = "водосчетчики";
        url = "https://stroypark.su/catalog/injenernyie-sistemyi-i-oborudovanie/sistemyi-vodosnabjeniya-i-kanalizatsii/schetchiki-rashoda-vodyi";

        urlGroup = new UrlGroup(groupe, url);
        urlGroupeRepository.save(urlGroup);


        model.addAttribute("table", urlGroupeRepository.getAll());
        return "indexgroupe";

    }
}
