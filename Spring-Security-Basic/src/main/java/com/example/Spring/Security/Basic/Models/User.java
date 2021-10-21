package com.example.Spring.Security.Basic.Models;

public class User {
     private Integer id;
     private String userName;
    private String password ;
    private Boolean active ;
    private String roles ;
    private String userType;

     public User(Integer id, String userName, String password , Boolean active, String roles,String userType ){
         this.id = id;
         this.userName = userName;
         this.password = password;
         this.active = active;
         this.roles = roles;
         this.userType = userType;
     }


    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public String getUserType() {
        return userType;
    }
}
