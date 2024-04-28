package com.scaler.firstspringapi.services;

import com.scaler.firstspringapi.models.Product;

import java.util.List;


public interface ProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Product product);
}
