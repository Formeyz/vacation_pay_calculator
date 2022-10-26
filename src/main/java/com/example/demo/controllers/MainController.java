package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {

    @PostMapping("/")
    public String postCalculation(@ModelAttribute("salary") String salary,
                                  @ModelAttribute("vacation-days") String vacationDays,
                                  @ModelAttribute("date") String date,
                                  Model model) {
        int iVacationDays;
        double dSalary;
        String result;
        try {
            iVacationDays = Integer.parseInt(vacationDays);
            dSalary = Double.parseDouble(salary);
            if (!Objects.equals(date, "")) {
                Set<String> holidayList = new HashSet<>();
                holidayList.add("1-1");
                holidayList.add("1-2");
                holidayList.add("1-3");
                holidayList.add("1-4");
                holidayList.add("1-5");
                holidayList.add("1-6");
                holidayList.add("1-7");
                holidayList.add("1-8");
                holidayList.add("2-23");
                holidayList.add("3-8");
                holidayList.add("5-1");
                holidayList.add("5-9");
                holidayList.add("6-12");
                holidayList.add("11-4");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(sdf.parse(date));
                for (int i = 0; i < iVacationDays; i++) {
                    if (holidayList.contains((calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH))) {
                        iVacationDays++;
                    }
                    calendar.add(Calendar.DATE, 1);
                }
                System.out.println(iVacationDays);
            }
            result = String.format("%.2f", dSalary / 29.3d * iVacationDays);
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
