package com.duje.projekt.Controller;

import com.duje.projekt.Service.ReservationService;
import com.duje.projekt.Service.TripService;
import com.duje.projekt.Service.UserService;
import com.duje.projekt.model.ReservationModel;
import com.duje.projekt.model.TripModel;
import com.duje.projekt.model.UserModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/add")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new ReservationModel());
        model.addAttribute("trips", tripService.getAllAvailableTrips());
        model.addAttribute("users", userService.getAllUsers());
        return "reservation_form";
    }

    @PostMapping("/add")
    public String addReservation(@ModelAttribute ReservationModel reservation,
                                 @RequestParam Integer tripId,
                                 @RequestParam Integer userId) {
        TripModel trip = tripService.getTripById(tripId);
        UserModel user = userService.getUserById(userId);

        reservation.setTrip(trip);
        reservation.setUser(user);

        reservationService.saveReservation(reservation);

        return "redirect:/superuser/dashboard";
    }


    @PostMapping("/reserve")
    public String reserveTrip(@RequestParam Integer tripId, HttpSession session, RedirectAttributes redirectAttributes) {
        UserModel user = (UserModel) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        TripModel trip = tripService.getTripById(tripId);

        if (trip == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid Trip!");
            return "redirect:/userPage";
        }

        ReservationModel reservation = new ReservationModel();
        reservation.setTrip(trip);
        reservation.setUser(user);

        reservationService.saveReservation(reservation);

        redirectAttributes.addFlashAttribute("successMessage", "Reservation successful!");
        return "redirect:/userPage";
    }

    @GetMapping("/complete/{tripId}")
    public String showReservationForm(@PathVariable Integer tripId, Model model, HttpSession session) {
        TripModel trip = tripService.getTripById(tripId);
        UserModel user = (UserModel) session.getAttribute("user");

        if (trip == null || user == null) {
            return "error_page";
        }

        ReservationModel reservation = new ReservationModel();
        reservation.setTrip(trip);
        reservation.setUser(user);

        model.addAttribute("reservation", reservation);
        return "complete_reservation_form";
    }


    @PostMapping("/complete")
    public String completeReservation(@ModelAttribute ReservationModel reservation,
                                      @RequestParam String notes,
                                      HttpSession session, RedirectAttributes redirectAttributes) {

        reservation.setStatus("Pending");
        reservation.setNotes(notes);
        reservationService.saveReservation(reservation);

        redirectAttributes.addFlashAttribute("successMessage", "Reservation successfully created and pending approval.");
        return "redirect:/userPage";
    }

}
