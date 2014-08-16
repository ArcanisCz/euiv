package com.springapp.mvc;

import cz.arcanis.euiv.NewMain;
import cz.arcanis.euiv.Parser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import java.io.File;

@Controller
@RequestMapping("/getSave")
public class AjaxController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String printWelcome(ModelMap model, @RequestParam(value = "pokus", required = true) String pokus) {
        try {
            System.out.println(pokus);
            String str = NewMain.getJsonFromFile(pokus);
            return str;
        } catch (Exception ex) {
            return "error";
        }
    }
}