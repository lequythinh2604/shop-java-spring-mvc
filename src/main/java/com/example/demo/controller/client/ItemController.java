package com.example.demo.controller.client;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public String getProductItemPage(Model model, @PathVariable Long id) {
        Product product = productService.findOneById(id).get();
        model.addAttribute("product", product);
        return "client/product/detail";
    }
}
