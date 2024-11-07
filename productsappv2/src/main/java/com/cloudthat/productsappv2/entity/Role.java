package com.cloudthat.productsappv2.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    Role(String user) {

    }

    @Override
    public String getAuthority() {
        return "";
    }
}
