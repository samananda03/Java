
package com.cloudthat.productsapp.controller;

import com.cloudthat.productsapp.entity.Product;
import com.cloudthat.productsapp.model.ApiResponse;
import com.cloudthat.productsapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }

//    @GetMapping("/products/{productId}")
//    public Product getProductByName(@PathVariable("productId") Long productId){
//        return productService.getProduct(productId);
//    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable("productId") Long productId){
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product Fetch Successful", productService.getProduct(productId)), HttpStatus.OK );
    }

    @PutMapping("/products/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product){
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "Product Deleted Successfully";
//        return  productService.deleteProduct(productId);
    }

    @GetMapping("/products/names/{productName}")
    public Product getProductByName(@PathVariable("productName") String productName){
        return productService.getProductByName(productName);
    }
}
