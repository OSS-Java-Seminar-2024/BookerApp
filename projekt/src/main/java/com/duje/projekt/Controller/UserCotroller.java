package com.duje.projekt.Controller;


import com.duje.projekt.Service.TripService;
import com.duje.projekt.Service.UserService;
import com.duje.projekt.model.TripModel;
import com.duje.projekt.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserCotroller {

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "register_page";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String postRegisterPage(@ModelAttribute UserModel user) {
        System.out.println("register request:" + user);
        UserModel registeredUser= userService.registerUser(user.getUsername(),user.getPassword(),user.getEmail(),user.getFirstName(), user.getLastName(), user.getPhone(),user.getRole());
        return registeredUser == null ?  "error_page" :  "redirect:/login";
    }

    @PostMapping("/login")
    public String postLoginPage(@ModelAttribute UserModel user,Model model) {
        System.out.println("Login request received for username: " + user.getUsername());
        System.out.println("Received raw password: " + user.getPassword()); // Debugging

        UserModel authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());

        if (authenticatedUser != null) {
            System.out.println("Login successful: " + authenticatedUser.getUsername());
            model.addAttribute("user", authenticatedUser);

            List<TripModel> availableTrips = tripService.getAllAvailableTrips();
            model.addAttribute("availableTrips", availableTrips);



            if(authenticatedUser.getRole().equals("ADMIN")) {
                return "admin_page";
            }
            else {
                return "user_page";
            }
        } else {
            System.out.println("Login failed for user: " + user.getUsername());
            return "error_page";
        }
    }

}
