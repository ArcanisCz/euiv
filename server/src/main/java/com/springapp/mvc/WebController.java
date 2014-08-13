package com.springapp.mvc;

import cz.arcanis.euiv.NewMain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class WebController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "home";
    }
}