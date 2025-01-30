package com.duje.projekt.Controller;


import com.duje.projekt.Service.TripService;
import com.duje.projekt.Service.UserService;
import com.duje.projekt.model.TripModel;
import com.duje.projekt.model.UserModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String postLoginPage(@RequestParam String username, @RequestParam String password, HttpSession session) {
        System.out.println("Login request received for username: " + username);
        System.out.println("Received raw password: " + password); 

        UserModel authenticatedUser = userService.authenticateUser(username, password);

        if (authenticatedUser != null) {
            System.out.println("Login successful: " + authenticatedUser.getUsername());
            session.setAttribute("user", authenticatedUser);

            if ("ADMIN".equals(authenticatedUser.getRole())) {
                return "redirect:/admin/dashboard";
            }
            if("SUPERUSER".equals(authenticatedUser.getRole())) {
                return "redirect:/superuser/dashboard";
            }
            else {
                return "redirect:/userPage";
            }
        } else {
            System.out.println("Login failed for user: " + username);
            return "error_page";
        }
    }
    @GetMapping("/user-trips/{id}")
    public String getUserTrips(@PathVariable Integer id, Model model) {
        UserModel user = userService.getUserById(id);
        if (user == null) {
            return "error_page";
        }

        List<TripModel> userTrips = tripService.getTripsByUserId(id);
        model.addAttribute("user", user);
        model.addAttribute("trips", userTrips);

        return "user_trips_page"; 
    }


}
