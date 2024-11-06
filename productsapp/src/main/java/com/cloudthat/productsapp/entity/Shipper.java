package com.cloudthat.productsapp.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shipperName;

    @ManyToMany(mappedBy = "shippers", cascade = CascadeType.ALL)
    private Set<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Shipper(String shipperName, Set<Product> products) {
        this.shipperName = shipperName;
        this.products = products;
    }

    public Shipper() {
    }
}