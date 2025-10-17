package com.example.demo.controller.admin;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.UploadService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = productService.handleFindAll();
        model.addAttribute("products", products);
        return "admin/product/index";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(@ModelAttribute("newProduct") @Valid Product product,
                                BindingResult bindingResult,
                                @RequestParam("imgFile") MultipartFile file
    ) {
        // log errors
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            System.out.println(">>> " + fieldError.getField() + " - " + fieldError.getDefaultMessage());
        }

        // check error
        if (bindingResult.hasErrors()) {
            return "admin/product/create";
        }

        String productImage = uploadService.handleSaveUploadFile(file, "product");
        product.setImage(productImage);

        productService.handleSave(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("requestProduct", new Product());
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String deleteProduct(@ModelAttribute("requestProduct") Product requestProduct) {
        productService.handleDelete(requestProduct.getId());
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable("id") Long id) {
        Product product = productService.findOneById(id).get();
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable Long id) {
        Product currentProduct = productService.findOneById(id).get();
        model.addAttribute("currentProduct", currentProduct);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String updateProduct(@ModelAttribute("currentProduct") @Valid Product product,
                                BindingResult bindingResult,
                                @RequestParam("productFile") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "admin/product/update";
        }

        Product currentProduct = productService.findOneById(product.getId()).get();

        if (currentProduct != null) {
            if (!file.isEmpty()) {
                String productImage = uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setImage(productImage);
            }

            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setQuantity(product.getQuantity());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setFactory(product.getFactory());
            currentProduct.setTarget(product.getTarget());

            productService.handleSave(currentProduct);
        }
        return "redirect:/admin/product";
    }

}
