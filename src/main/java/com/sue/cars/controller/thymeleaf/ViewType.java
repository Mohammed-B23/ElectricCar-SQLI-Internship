package com.sue.cars.controller.thymeleaf;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewType {
    @Autowired
    private ServletContext servletContext;

    @GetMapping("/update-view-type")
    @ResponseBody
    public ResponseEntity<String> updateViewType(@RequestParam String newViewType) {
//        model.addAttribute("viewTypeS", newViewType);
        CommonAttributesAdvice.updateDefaultViewType(newViewType);
        return ResponseEntity.ok("View type updated successfully");
    }
}
