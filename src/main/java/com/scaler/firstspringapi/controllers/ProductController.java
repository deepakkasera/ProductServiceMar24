package com.scaler.firstspringapi.controllers;

import com.scaler.firstspringapi.models.Product;
import com.scaler.firstspringapi.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    @GetMapping("/{id}")//localhost:8080/product/1
    public Product getProductById(@PathVariable Long id){
       return productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
       return productService.getAllProducts();
    }

    @PatchMapping()
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

}
