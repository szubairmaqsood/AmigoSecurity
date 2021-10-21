package com.example.Spring.Security.Basic.Security;

import com.example.Spring.Security.Basic.Enums.ApplicationUserPermission;
import com.example.Spring.Security.Basic.Enums.ApplicationUserRoles;
import com.example.Spring.Security.Basic.jwt.JwtTokenVerifier;
import com.example.Spring.Security.Basic.jwt.JwtUserNameAndAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration

public class WebSecurityConfigurations extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailService;
    //private final AuthenticationManager authenticationManager;


    @Autowired
    WebSecurityConfigurations(PasswordEncoder _passwordEncode,  UserDetailsService userDetailService
    // ,AuthenticationManager authenticationManager
    ){
        this.passwordEncoder = _passwordEncode;
        this.userDetailService = userDetailService;
       // this.authenticationManager = authenticationManager;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        http
                .csrf().disable()
                 // Session Managment
               // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Filter addition
               // .and()
               //.addFilter(new JwtUserNameAndAuthenticationFilter(authenticationManager()))
               // .addFilterAfter(new JwtTokenVerifier(),JwtUserNameAndAuthenticationFilter.class)


                // Related to request
                .authorizeRequests()
                //.antMatchers("/login").permitAll()
                .antMatchers("/api/v1/public").permitAll()

                //.antMatchers("/api/v1/public").access("@wtUserNameAndAuthenticationFilter(authenticationManager.checkAccess(authentication,request)")
                .antMatchers("/api/v1/students").hasRole(ApplicationUserRoles.STUDENT.name())
                .antMatchers(HttpMethod.GET,"/api/v1/managment").hasAnyRole(ApplicationUserRoles.ADMINTRAINEE.name(),ApplicationUserRoles.ADMIN.name())
                .antMatchers("/api/v1/managment").hasRole(ApplicationUserRoles.ADMIN.name())

                //.antMatchers("/api/v1/students").hasAnyAuthority(ApplicationUserPermission.STUDENT_READ.getPermission(),ApplicationUserPermission.STUDENT_WRITE.getPermission())
                //.antMatchers(HttpMethod.GET,"/api/v1/managment").hasAnyRole(ApplicationUserRoles.ADMINTRAINEE.name(),ApplicationUserRoles.ADMIN.name())
                //.antMatchers("/api/v1/managment").hasRole(ApplicationUserRoles.ADMIN.name())
                //.antMatchers("/api/v1/managment").access("@userSecurity.hasUserId(authentication,#userId)")

        .anyRequest()
                .authenticated()
                .and()
               .httpBasic();
    }



    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
      // return this.userDetailService;


        UserDetails student = User.builder()
                .username("student")
                .password(this.passwordEncoder.encode("123456"))
               .roles(ApplicationUserRoles.STUDENT.name())
             //   .authorities(ApplicationUserRoles.STUDENT.getGrandtedAuthorities())

                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(this.passwordEncoder.encode("123456"))
               .roles(ApplicationUserRoles.ADMIN.name())
                //.authorities(ApplicationUserRoles.ADMIN.getGrandtedAuthorities())
                .build();

        UserDetails adminTrainee = User.builder()
                .username("adminTrainee")
                .password(this.passwordEncoder.encode("123456"))
                .roles(ApplicationUserRoles.ADMINTRAINEE.name())
               // .authorities(ApplicationUserRoles.ADMINTRAINEE.getGrandtedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(student,admin,adminTrainee);


    }



}
