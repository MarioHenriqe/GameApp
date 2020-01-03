package com.gameapp.gameform.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String Index(){

        return "index";
    }
}
