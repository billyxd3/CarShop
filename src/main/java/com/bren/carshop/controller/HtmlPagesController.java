package com.bren.carshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlPagesController {

    @RequestMapping("/adminpage")
    public String adminPage() {
        return "adminPage.html";
    }

    @RequestMapping("/admincountry")
    public String adminCountries() {
        return "country.html";
    }

    @RequestMapping("/admincar")
    public String adminCar() {
        return "car.html";
    }

    @RequestMapping("/adminmake")
    public String adminMake() {
        return "make.html";
    }

    @RequestMapping("/adminmodel")
    public String adminModel() {
        return "model.html";
    }

    @RequestMapping("/admincity")
    public String adminCity() {
        return "city.html";
    }

    @RequestMapping("/adminbodytype")
    public String adminBodyType() {
        return "bodyType.html";
    }

    @RequestMapping("/admincolor")
    public String admincolor() {
        return "color.html";
    }

    @RequestMapping("/admindrivertype")
    public String adminDriverType() {
        return "driverType.html";
    }

    @RequestMapping("/adminfuel")
    public String adminFuel() {
        return "fuel.html";
    }

    @RequestMapping("/admingearbox")
    public String adminGearbox() {
        return "gearbox.html";
    }

    @RequestMapping("/catalog")
    public String catalog() {
        return "catalog.html";
    }

    @RequestMapping("/item")
    public String carCatalog() {
        return "carCatalog.html";
    }

    @RequestMapping("/index")
    public String index() {
        return "index.html";
    }
}
