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


    @GetMapping("/edit-user/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        UserModel user = userService.getUserById(id);
        if (user == null) {
            return "error_page";
        }
        model.addAttribute("user", user);
        return "edit_user_page";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute UserModel user) {
        userService.updateUser(user);
        return "redirect:/superuser/dashboard";
    }

    @GetMapping("/edit-trip/{id}")
    public String editTripForm(@PathVariable Integer id, Model model) {
        TripModel trip = tripService.getTripById(id);
        if (trip == null) {
            return "error_page";
        }
        List<UserModel> bookers = userService.getUsersByRole("ADMIN");
        model.addAttribute("trip", trip);
        model.addAttribute("bookers", bookers);
        return "edit_trip_page";
    }

    @PostMapping("/update-trip")
    public String updateTrip(@ModelAttribute TripModel trip, @RequestParam String bookerUsername) {
        UserModel booker = userService.getUserByUsername(bookerUsername);
        if (booker != null) {
            trip.setBooker(booker);
        }
        tripService.saveTrip(trip);
        return "redirect:/superuser/dashboard";
    }

}

