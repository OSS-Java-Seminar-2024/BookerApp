package com.duje.seminar.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");

        modelAndView.addObject("message", "Hello World");

        return modelAndView;
    }
}
