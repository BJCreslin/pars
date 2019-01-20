package ru.bjcreslin.pars.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.pars.Service.URLGroupeServiceImpl;
import ru.bjcreslin.pars.model.UrlGroup;

@Slf4j
@Controller
@RequestMapping("/groupe")
public class Groupe {

    @Autowired
    URLGroupeServiceImpl urlGroupeService;

    @RequestMapping("/init")
    public String initGroupe(Model model) {
        String groupeName;
        String url;
        UrlGroup urlGroup;

        groupeName = "насосы";
        url = "https://stroypark.su/catalog/injenernyie-sistemyi-i-oborudovanie/sistemyi-vodosnabjeniya-i-kanalizatsii/nasosnoe-oborudovanie";

        urlGroup = new UrlGroup();
        urlGroup.setNameGroupe(groupeName);
        urlGroup.setUrlGroupe(url);
        urlGroupeService.save(urlGroup);


        groupeName = "расширительные баки";
        url = "https://stroypark.su/catalog/injenernyie-sistemyi-i-oborudovanie/sistemyi-vodosnabjeniya-i-kanalizatsii/baki-rasshiritelnyie";

        urlGroup = new UrlGroup();
        urlGroup.setNameGroupe(groupeName);
        urlGroup.setUrlGroupe(url);
        urlGroupeService.save(urlGroup);


        groupeName = "водосчетчики";
        url = "https://stroypark.su/catalog/injenernyie-sistemyi-i-oborudovanie/sistemyi-vodosnabjeniya-i-kanalizatsii/schetchiki-rashoda-vodyi";

        urlGroup = new UrlGroup();
        urlGroup.setNameGroupe(groupeName);
        urlGroup.setUrlGroupe(url);
        urlGroupeService.save(urlGroup);

        model.addAttribute("table", urlGroupeService.getAll());
        return "indexgroupe";

    }
}
