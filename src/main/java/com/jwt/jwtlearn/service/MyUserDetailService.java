package com.jwt.jwtlearn.service;

import com.jwt.jwtlearn.entity.MyUserDetails;
import com.jwt.jwtlearn.entity.User;
import com.jwt.jwtlearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    public MyUserDetailService() {
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
       Optional<User> user =  userRepo.findByName(name);
        return user
                .map(MyUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("Username does not exist!!!"));
    }
}
