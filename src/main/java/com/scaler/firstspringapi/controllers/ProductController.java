package com.scaler.firstspringapi.controllers;

import com.scaler.firstspringapi.exceptions.ProductNotFoundException;
import com.scaler.firstspringapi.models.Product;
import com.scaler.firstspringapi.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private ProductService productService;
    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }


    //localhost:8080/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
//        if (product == null) {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            return responseEntity;
//        }

//        Product sampleProduct = new Product();

        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;

//        ResponseEntity<Product> responseEntity = null;
//        try {
//            Product product = productService.getProductById(id);
//            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
//        } catch (ArithmeticException e) {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        //Controllers should be as light as possible.
//        return responseEntity;
        //throw new RuntimeException("Something went wrong");
    }

    // localhost:8080/products
    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    //createProduct
    //deleteProduct
    //updateProduct -> Partial Update (PATCH)
    //replaceProduct -> Replace (PUT)

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Void> handleSomeException() {
        return null;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) { // can use DTO as well.
        return productService.createProduct(product);
    }

}
