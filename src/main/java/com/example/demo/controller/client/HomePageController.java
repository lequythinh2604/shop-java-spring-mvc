package com.example.demo.controller.client;

import com.example.demo.domain.Product;
import com.example.demo.domain.dto.RegisterDTO;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = productService.handleFindAll();
        model.addAttribute("products", products);
        return "client/homepage/index";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {

        return "client/auth/login";
    }

}
