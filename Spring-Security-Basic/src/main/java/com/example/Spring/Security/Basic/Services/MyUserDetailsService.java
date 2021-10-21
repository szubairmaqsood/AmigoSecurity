package com.example.Spring.Security.Basic.Services;

import com.example.Spring.Security.Basic.Models.MyUserDetails;
import com.example.Spring.Security.Basic.Models.User;
import com.example.Spring.Security.Basic.Repositroy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    UserRepository userRepositroy ;

    @Autowired
    MyUserDetailsService( UserRepository _userRepositroy){
        this.userRepositroy =_userRepositroy;
    }
    @Override
  public UserDetails loadUserByUsername(String userName )  {
           User user =  this.userRepositroy.findByUserName(userName);


            MyUserDetails uDetail = new MyUserDetails(user.getUserName(),user.getPassword(),user.getActive(),user.getRoles(),user.getUserType());
            uDetail.setUserName(user.getUserName());

            return  uDetail;

    }
}
