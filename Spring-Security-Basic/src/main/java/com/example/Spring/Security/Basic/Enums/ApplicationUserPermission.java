package com.example.Spring.Security.Basic.Enums;

public enum ApplicationUserPermission {
    STUDENT_READ("student_read"),
    STUDENT_WRITE("student_write"),
    MANAGMENT_READ("managment_read"),
    MANAGMENT_WRITE("managment_write");

    private final String Permission ;

     ApplicationUserPermission(String permission){
        this.Permission = permission;
    }


    public String getPermission() {
        return Permission;
    }
}
