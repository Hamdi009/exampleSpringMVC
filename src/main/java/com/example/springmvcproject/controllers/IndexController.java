package com.example.springmvcproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value="/")
    public String welcome(){
        return "index";
    }
}