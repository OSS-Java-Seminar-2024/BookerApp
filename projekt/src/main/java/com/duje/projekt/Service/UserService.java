package com.duje.projekt.Service;


import com.duje.projekt.Repository.UserRepository;
import com.duje.projekt.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserModel registerUser(String username, String password, String email,
                                  String firstName, String lastName, String phone, String role) {
        if (username != null && password != null && email != null && firstName != null
                && lastName != null && phone != null) {

            if (userRepository.findFirstByUsername(username).isPresent()) {
                System.out.println("Username already exists");
                return null;
            }

            UserModel user = new UserModel();
            user.setUsername(username);
            user.setPassword(encoder.encode(password));
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setRole(role);

            return userRepository.save(user);
        }
        return null;
    }


    public UserModel authenticateUser(String username, String rawPassword) {
        Optional<UserModel> optionalUser = userRepository.findFirstByUsername(username);

        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            System.out.println("Stored password hash: " + user.getPassword()); // Debugging

            if (encoder.matches(rawPassword, user.getPassword())) {
                System.out.println("Password match successful");
                return user; // Authentication successful
            } else {
                System.out.println("Password mismatch");
            }
        } else {
            System.out.println("User not found");
        }
        return null; // Authentication failed
    }
}
