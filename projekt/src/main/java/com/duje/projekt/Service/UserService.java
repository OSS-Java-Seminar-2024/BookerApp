package com.duje.projekt.Service;


import com.duje.projekt.Repository.UserRepository;
import com.duje.projekt.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserModel registerUser(UserModel user) {
        if (user.getUsername() != null && user.getPassword() != null && user.getEmail() != null
                && user.getFirstName() != null && user.getLastName() != null && user.getPhone() != null) {

            if (userRepository.findFirstByUsername(user.getUsername()).isPresent()) {
                System.out.println("Username already exists");
                return null;
            }
            
            user.setPassword(encoder.encode(user.getPassword()));

            // Ako rola nije postavljena,ovo je defultna
            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("USER");
            }

            return userRepository.save(user);
        }
        return null;
    }



    //znaci zahesirat i onda trazit ovako triba:
    public UserModel authenticateUser(String username, String rawPassword) {
        Optional<UserModel> optionalUser = userRepository.findFirstByUsername(username);

        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            System.out.println("Stored password hash: " + user.getPassword());

            if (encoder.matches(rawPassword, user.getPassword())) {
                System.out.println("Password match successful");
                return user; // Authentication successful
            } else {
                System.out.println("Password mismatch");
            }
        } else {
            System.out.println("User not found");
        }
        return null; //authentication failed
    }


    public UserModel getUserByUsername(String username) {
        return userRepository.findFirstByUsername(username).orElse(null);
    }

    public void saveUser(UserModel user) {
        userRepository.save(user);
    }

    public List<UserModel> getUsersByRole(String role) {
        List<UserModel> users = userRepository.findByRole(role);
        //System.out.println("Found users with role " + role + ": " + users.size()); 
        return users;
    }
 
    public List<UserModel> getAllUsers() {
        return userRepository.findAll(); 
    }

}
