package ru.bjcreslin.pars.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.pars.Service.ProductServiceImpl;
import ru.bjcreslin.pars.Service.URLGroupeServiceImpl;
import ru.bjcreslin.pars.model.Product;
import ru.bjcreslin.pars.model.UrlGroup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/product")
public class StroyparkRestController {

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    URLGroupeServiceImpl urlGroupeService;

    @SneakyThrows
    @RequestMapping("/action")
    public String action(Model model) {

        log.info("запущено");
        List<Product> allProducts = new ArrayList<>();

        String adress;

        for (UrlGroup groupe : urlGroupeService.getAll()) {
            adress = groupe.getUrlGroupe();
            Document document;

            document = Jsoup.connect(adress).
                    userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 " +
                            "Safari/537.36").
                    get();

            String textOUT = document.title();

            log.info(textOUT);

            Elements elements = document.getElementsByClass("c-product-list__item clearfix node node-good node-teaser");
            for (Element element : elements) {

                String name = element.getElementsByClass("c-product-list__title").text();

                String costStringNotPrior = element.getElementsByClass("c-product__price-value c-product__price-value--not-prior").text();

                String costStringWithPrior = element.getElementsByClass("c-product__price-value o-color--orange").text();

                Elements element1 = element.getElementsByClass("c-product-list__media");
                Elements elementSelect = element1.select("img");
                String imgURL = elementSelect.attr("src");
                Element elementSelectS = element1.select("a").first();
                String productURL = "https://stroypark.su" + elementSelectS.attr("href");


                if (costStringNotPrior.isEmpty()) {
                    costStringNotPrior = element.getElementsByClass("c-product__price-value").text();
                }

                BigDecimal cost = StringConroller.stroyparkCostToBigDecimal(costStringNotPrior);
                BigDecimal costWithPrior = StringConroller.stroyparkCostToBigDecimal(costStringWithPrior);
                log.info("{}--{}--{}", name, cost, costWithPrior);


                Product product = new Product();
                product.setName(name);
                product.setAdress(productURL);
                product.setCost(cost);
                product.setAdressIMG(imgURL);
                product.setGroup(groupe.getNameGroupe());
                product.setCostWithPrior(costWithPrior);


                allProducts.add(product);
                productService.save(product);

            }
        }
        model.addAttribute("table", productService.getAll());


        return "index";


    }

    @RequestMapping("/listall")
    public String listAll(Model model) {
        model.addAttribute("table", productService.getAll());
        return "index";
    }
}
