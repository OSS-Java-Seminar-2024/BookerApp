package com.duje.projekt.Controller;

import com.duje.projekt.Service.ReservationService;
import com.duje.projekt.Service.TripService;
import com.duje.projekt.Service.UserService;
import com.duje.projekt.model.ReservationModel;
import com.duje.projekt.model.TripModel;
import com.duje.projekt.model.UserModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/userPage")
    public String getUserPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        UserModel authenticatedUser = (UserModel) session.getAttribute("user");

        if (authenticatedUser == null) {
            return "redirect:/login";
        }

        List<TripModel> availableTrips = tripService.getAllAvailableTrips();
        model.addAttribute("user", authenticatedUser);
        model.addAttribute("availableTrips", availableTrips);

        return "user_page";
    }

    @GetMapping("/userReservations")
    public String getUserReservations(HttpSession session, Model model) {
        UserModel authenticatedUser = (UserModel) session.getAttribute("user");

        if (authenticatedUser == null) {
            return "redirect:/login";
        }

        // Dohvati sve rezervacije za korisnika
        List<ReservationModel> userReservations = reservationService.getUserReservations(authenticatedUser.getId());
        model.addAttribute("userReservations", userReservations);

        return "user_reservation_page";
    }

    @PostMapping("/deleteReservation")
    public String deleteReservation(@RequestParam Integer reservationId, RedirectAttributes redirectAttributes) {
        reservationService.deleteReservation(reservationId);
        redirectAttributes.addFlashAttribute("successMessage", "Reservation deleted successfully.");
        return "redirect:/userReservations";
    }

    // Ažuriranje notes polja
    @PostMapping("/updateReservationNotes")
    public String updateReservationNotes(@RequestParam Integer reservationId, @RequestParam String notes, RedirectAttributes redirectAttributes) {
        reservationService.updateReservationNotes(reservationId, notes);
        redirectAttributes.addFlashAttribute("successMessage", "Reservation notes updated successfully.");
        return "redirect:/userReservations";
    }

}
