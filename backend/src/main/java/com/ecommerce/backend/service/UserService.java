package com.ecommerce.backend.service;

import com.ecommerce.backend.model.User;
import com.ecommerce.backend.repository.UserRepo;
import com.ecommerce.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String loginUser(String email, String password){
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent() && passwordEncoder.matches(password,user.get().getPassword()) ){
            return jwtUtil.generateToken(email);


        }else {
            throw new RuntimeException("Incorrect email or password");
        }
    }

}
