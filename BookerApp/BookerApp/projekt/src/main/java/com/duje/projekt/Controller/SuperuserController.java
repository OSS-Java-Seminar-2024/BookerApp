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
        // Dohvati postojeći trip iz baze
        TripModel existingTrip = tripService.getTripById(trip.getId());
        if (existingTrip == null) {
            return "error_page"; // Možeš preusmjeriti na custom error stranicu
        }

        // Logiranje za bookerUsername
        System.out.println("Booker username: " + bookerUsername);

        // Dohvati bookera na temelju username-a
        UserModel booker = userService.getUserByUsername(bookerUsername);

        // Logiranje nakon dohvaćanja bookera
        if (booker == null) {
            System.out.println("No booker found for username: " + bookerUsername);
        } else {
            System.out.println("Booker found: " + booker.getUsername());
        }

        // Postavi podatke za trip
        existingTrip.setTripName(trip.getTripName());
        existingTrip.setTripDate(trip.getTripDate());
        existingTrip.setTripTime(trip.getTripTime());
        existingTrip.setTripDescription(trip.getTripDescription());
        existingTrip.setTripLocation(trip.getTripLocation());
        existingTrip.setTripCapacity(trip.getTripCapacity());
        //existingTrip.setBooker(trip.getBooker());

        // Provjera i dodavanje bookera
        if (booker != null) {
            existingTrip.setBooker(booker);
        } else {
            // Ako booker nije pronađen, možeš obraditi ovo (npr. prikazivanje poruke o grešci)
            return "error_page";
        }

        // Spremi trip
        tripService.saveTrip(existingTrip);

        return "redirect:/superuser/dashboard";
    }

}

