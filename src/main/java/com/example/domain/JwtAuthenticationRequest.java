package com.example.domain;

import org.springframework.data.annotation.PersistenceConstructor;

/**
 * Created by vxd4 on 2/12/2017.
 */
public class JwtAuthenticationRequest {

    private String username;
    private String password;

    @PersistenceConstructor
    public JwtAuthenticationRequest() {

    }

    @PersistenceConstructor
    public JwtAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
