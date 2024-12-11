package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-form";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") Long id, @Valid @ModelAttribute ProductEntity product, BindingResult result) {
        if (result.hasErrors()) {
            return "product-form";
        }
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list";
    }

//    @GetMapping("/{id}")
//    public String getProductById(@PathVariable Long id, Model model) {
//        model.addAttribute("product", productService.getProductById(id));
//        return "product-details";
//    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductEntity());
        return "product-form";
    }

    @PostMapping
    public String addProduct(@Valid @ModelAttribute ProductEntity product, BindingResult result) {
        if (result.hasErrors()) {
            return "product-form";
        }
        productService.addProduct(product);
        return "redirect:/products";
    }

//    @GetMapping("/edit/{id}")
//    public String showEditProductForm(@PathVariable Long id, Model model) {
//        model.addAttribute("product", productService.getProductById(id));
//        return "product-form";
//    }

//    @PutMapping("/{id}")
//    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute ProductEntity product, BindingResult result) {
//        if (result.hasErrors()) {
//            return "product-form";
//        }
//        productService.updateProduct(id, product);
//        return "redirect:/products";
//    }

//    @DeleteMapping("/{id}")
//    public String deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        return "redirect:/products";
//    }
//    
}
