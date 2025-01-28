package com.duje.projekt.Controller;


import com.duje.projekt.model.TripModel;
import com.duje.projekt.model.UserModel;
import com.duje.projekt.Service.TripService;
import com.duje.projekt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/superuser")
public class SuperuserController {

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String superuserPage(Model model) {
        List<TripModel> trips = tripService.getAllAvailableTrips();
        List<UserModel> admins = userService.getUsersByRole("ADMIN");
        List<UserModel> allUsers = userService.getAllUsers(); 


        model.addAttribute("trips", trips);
        model.addAttribute("admins", admins);  
        model.addAttribute("users", allUsers); 
        model.addAttribute("bookers", admins); 
        model.addAttribute("allUsers", allUsers);

        return "superuser_page";
    }


    @PostMapping("/add-trip")
    public String addTrip(@ModelAttribute TripModel trip, @RequestParam String bookerUsername) {
        try {
            tripService.saveTripWithBooker(trip, bookerUsername);
            return "redirect:/superuser/dashboard";
        } catch (IllegalArgumentException e) {
            return "error_page"; 
        }
    }
}

