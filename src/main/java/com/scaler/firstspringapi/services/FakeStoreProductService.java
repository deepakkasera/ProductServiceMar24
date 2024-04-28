package com.scaler.firstspringapi.services;

import com.scaler.firstspringapi.dtos.FakeStoreProductDto;
import com.scaler.firstspringapi.models.Category;
import com.scaler.firstspringapi.models.Product;
import jakarta.persistence.Cache;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FakeStoreProductService implements  ProductService{

    private RestTemplate restTemplate;
    @Override
    public Product getProductById(Long id) {
        //call fake store api
       FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject(
               "https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
       if(fakeStoreProductDto == null) {
           return null;
       }
           return convertFakeStoreDtoToProdcutDto(fakeStoreProductDto);

    }
    @Override
    public List<Product> getAllProducts() {
        //call fake store api for get all products
        //generic type erasure
        FakeStoreProductDto[] fakeStoreProductDto =  restTemplate.getForObject(
                "https://fakestoreapi.com/products/", FakeStoreProductDto[].class);
        if(fakeStoreProductDto == null) {
            return null;
        }
        // convert fake store dto to product dto
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto: fakeStoreProductDto){
            products.add(convertFakeStoreDtoToProdcutDto(dto));
        }
        return products;
    }

    @Override
    public Product updateProduct(Product product) {
        FakeStoreProductDto dto = new FakeStoreProductDto();
        dto.setDescription(product.getDescription());
        dto.setTitle(product.getTitle());
        dto.setImage(product.getImage());
        dto.setCategory(product.getCategory().getTitle());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(dto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        dto = restTemplate.execute("https://fakestoreapi.com/products/7",HttpMethod.PUT,requestCallback,responseExtractor);
        return convertFakeStoreDtoToProdcutDto(dto);
    }


    private Product convertFakeStoreDtoToProdcutDto(FakeStoreProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        product.setDescription(dto.getDescription());
        Category category = new Category();
        category.setTitle(dto.getCategory());
        product.setCategory(category);
        return product;
    }
}
