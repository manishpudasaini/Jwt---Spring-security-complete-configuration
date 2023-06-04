package com.jwt.jwtlearn.service;

import com.jwt.jwtlearn.entity.User;
import com.jwt.jwtlearn.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    //add user in database
    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    //get all user from database
    public List<User> allUser(){
      return userRepo.findAll();
    }

    //get single user
    public User getUserById(long id){
       Optional<User> dbUser =  userRepo.findById(id);
       if (dbUser.isPresent()){
           User user = dbUser.get();
           return user;
       }else {
           throw new NoSuchElementException("User doesnt exist!!!");
       }
    }

}
