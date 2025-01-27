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
import org.springframework.web.bind.annotation.RequestParam;

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
        System.out.println("Register request: " + user);

        UserModel registeredUser = userService.registerUser(user);

        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String postLoginPage(@RequestParam String username, @RequestParam String password, Model model) {
        System.out.println("Login request received for username: " + username);
        System.out.println("Received raw password: " + password); // Debugging

        UserModel authenticatedUser = userService.authenticateUser(username, password);

        if (authenticatedUser != null) {
            System.out.println("Login successful: " + authenticatedUser.getUsername());
            model.addAttribute("user", authenticatedUser);

            List<TripModel> availableTrips = tripService.getAllAvailableTrips();
            model.addAttribute("availableTrips", availableTrips);

            if ("ADMIN".equals(authenticatedUser.getRole())) {
                return "admin_page";
            } else {
                return "user_page";
            }
        } else {
            System.out.println("Login failed for user: " + username);
            return "error_page";
        }
    }

}
