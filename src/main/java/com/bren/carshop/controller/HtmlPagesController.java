package com.bren.carshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlPagesController {

    @RequestMapping("/admin")
    public String adminCountries() {
        return "country.html";
    }

    @RequestMapping("/admincar")
    public String adminCar() {
        return "car.html";
    }

    @RequestMapping("/adminmake")
    public String adminMake() {
        return "maake.html";
    }

//    @RequestMapping("/admin/country")
//    public String adminCountries() {
//        return "country.html";
//    }
}
