package com.duje.projekt.Service;


import com.duje.projekt.Repository.UserRepository;
import com.duje.projekt.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel registerUser(String username, String password, String email){
        if(username != null && password != null ){
            if(userRepository.findFirstByUsername(username).isPresent()){
                System.out.println("Username already exists");
                return null;
            }
            UserModel user = new UserModel();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            return userRepository.save(user);
        }else{
            return null;
        }
    }


    public UserModel authenticateUser(String username, String password){
        return userRepository.findByUsernameAndPassword(username,password).orElse(null);
    }
}
