package com.dnu.klimmenkov.projectplanner.controller.admin;

import com.dnu.klimmenkov.projectplanner.entity.Product;
import com.dnu.klimmenkov.projectplanner.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/allProducts";
    }

    @GetMapping("/new")
    public String getAddProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "admin/addProduct";
    }

    @PostMapping()
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/addProduct";
        }
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/update/{id}")
    public String showEditProductForm(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/addProduct";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
