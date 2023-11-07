package com.sue.cars.controller.thymeleaf;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CommonAttributesAdvice {
    private static String defaultViewType = "card";

    @ModelAttribute
    public void addCommonAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("request", request);
        model.addAttribute("viewTypeS",defaultViewType);
    }

    public static void updateDefaultViewType(String newDefaultViewType) {
        defaultViewType = newDefaultViewType;
    }


}






