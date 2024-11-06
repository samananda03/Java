package com.cloudthat.productsapp.services;

import com.cloudthat.productsapp.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product saveProduct(Product product);

    List<Product> getProducts();

    Product getProduct(Long productId);

    Product updateProduct(Long productId, Product product);

    void deleteProduct(Long productId);

    Product getProductByName(String productName);
}