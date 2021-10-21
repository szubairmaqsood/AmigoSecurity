package com.example.Spring.Security.Basic.Enums;



import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


public enum ApplicationUserRoles {

    STUDENT(Sets.newHashSet(ApplicationUserPermission.STUDENT_READ,ApplicationUserPermission.STUDENT_WRITE)),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.STUDENT_READ,ApplicationUserPermission.STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(ApplicationUserPermission.STUDENT_READ,ApplicationUserPermission.STUDENT_WRITE));

    private Set<ApplicationUserPermission> applicationUserPermissionSets;

    ApplicationUserRoles(Set<ApplicationUserPermission> _applicationUserPermissionSets){
        this.applicationUserPermissionSets = _applicationUserPermissionSets;
    }

    public Set<SimpleGrantedAuthority> getGrandtedAuthorities(){
        Set<SimpleGrantedAuthority> permission = getApplicationUserPermissionSets().stream()
                .map(_permission ->new SimpleGrantedAuthority(_permission.getPermission()))
                .collect(Collectors.toSet());
        permission.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return permission;
    }


    public Set<ApplicationUserPermission> getApplicationUserPermissionSets() {
        return applicationUserPermissionSets;
    }
}
