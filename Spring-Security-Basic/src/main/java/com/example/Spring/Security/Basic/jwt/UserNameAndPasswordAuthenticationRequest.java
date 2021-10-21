package com.example.Spring.Security.Basic.jwt;
/*
This wull handle data provided by user
 */
public class UserNameAndPasswordAuthenticationRequest {
    private String username;
    private String password;

    public UserNameAndPasswordAuthenticationRequest() {
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
