package com.cloudthat.productsapp.model;

import com.cloudthat.productsapp.entity.Category;
import com.cloudthat.productsapp.entity.ProductProperties;
import com.cloudthat.productsapp.entity.Shipper;
import com.cloudthat.productsapp.entity.Warranty;
import java.time.LocalDateTime;
import java.util.Set;

public class ProductModel {

    private Long id;
    private String productName;
    private String productDescription;
    private double price;
    private boolean isEnabled;
    private Category category;
    private LocalDateTime createdAt;
    private ProductProperties productProperties;
    private Warranty warranty;
    private Set<Shipper> shippers;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ProductProperties getProductProperties() {
        return productProperties;
    }

    public void setProductProperties(ProductProperties productProperties) {
        this.productProperties = productProperties;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    public Set<Shipper> getShippers() {
        return shippers;
    }

    public void setShippers(Set<Shipper> shippers) {
        this.shippers = shippers;
    }

    public ProductModel(Long id, String productName, String productDescription, double price, boolean isEnabled, Category category, LocalDateTime createdAt, ProductProperties productProperties, Warranty warranty, Set<Shipper> shippers) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.isEnabled = isEnabled;
        this.category = category;
        this.createdAt = createdAt;
        this.productProperties = productProperties;
        this.warranty = warranty;
        this.shippers = shippers;
    }

    public ProductModel() {
    }
}