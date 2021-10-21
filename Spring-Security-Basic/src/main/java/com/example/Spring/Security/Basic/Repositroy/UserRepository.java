package com.example.Spring.Security.Basic.Repositroy;

import com.example.Spring.Security.Basic.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private final PasswordEncoder passwordEncoder;
    private List<User> myList = new ArrayList<User>();

    @Autowired
    UserRepository(PasswordEncoder _passwordEncode){
        this.passwordEncoder = _passwordEncode;
        myList.add(new User(1,"student" , this.passwordEncoder.encode("123456"), true,"STUDENT","s1"));
        myList.add(new User(2,"admin" , this.passwordEncoder.encode("123456"), true,"ADMIN","s2"));
        myList.add(new User(3,"adminTrainee" , this.passwordEncoder.encode("123456"), true,"ADMINTRAINEE","s3"));
    }

    public List<User> getAllStudents(){
        return this.myList;
    }

    public User  findByUserName(String userName){
        User user =this.myList.stream().filter(a -> a.getUserName().equals(userName)).collect(Collectors.toList()).get(0);
       // Optional<User> opt = Optional.ofNullable(user);
        return user;
    }
}
