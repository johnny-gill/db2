package com.example.db2.web;

import com.example.db2.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ItemService itemService;

    @RequestMapping("/")
    public String home() {
        return "redirect:/items";
    }
}
