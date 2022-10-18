package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @PostMapping("/")
    public String postCalculation(@ModelAttribute("salary") String salary,
                                  @ModelAttribute("vacation-days") String vacationDays,
                                  Model model) {
        String result;
        try {
            result = String.format("%.2f", Double.parseDouble(salary) / 29.3d * Double.parseDouble(vacationDays));
        } catch (Exception e) {
            result = "Некорректные данные!";
        }
        model.addAttribute("result", result);
        return "main-page";
    }

    @GetMapping("/")
    public String getCalculation() {
        return "main-page";
    }
}
