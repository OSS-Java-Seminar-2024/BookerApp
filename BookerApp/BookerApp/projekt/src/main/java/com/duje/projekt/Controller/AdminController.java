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

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private TripService tripService;

    @Autowired
    private ReservationService reservationService;


    @GetMapping("/dashboard")
    public String getAdminDashboard(HttpSession session, Model model) {
        UserModel authenticatedUser = (UserModel) session.getAttribute("user");

        if (authenticatedUser == null || !"ADMIN".equals(authenticatedUser.getRole())) {
            return "redirect:/login";
        }

        // Dohvat svih tripova bookera
        List<TripModel> adminTrips = tripService.getTripsByBooker(authenticatedUser.getId());
        model.addAttribute("adminTrips", adminTrips);

        // Dohvat pending rezervacija za njegove tripove
        List<ReservationModel> pendingReservations = reservationService.findPendingReservationsByBooker(authenticatedUser);
        model.addAttribute("pendingReservations", pendingReservations);

        return "admin_page";
    }

    @PostMapping("/confirm/{reservationId}")
    public String confirmReservation(@PathVariable Integer reservationId) {
        reservationService.updateReservationStatus(reservationId, "CONFIRMED");
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/decline/{reservationId}")
    public String declineReservation(@PathVariable Integer reservationId) {
        reservationService.updateReservationStatus(reservationId, "DECLINED");
        return "redirect:/admin/dashboard";
    }
    @GetMapping("/confirmedUsers/{tripId}")
    public String viewConfirmedUsers(@PathVariable Integer tripId, Model model) {
        // Dohvati potvrđene rezervacije za odabrani trip
        List<ReservationModel> confirmedReservations = reservationService.findConfirmedReservationsByTrip(tripId);
        model.addAttribute("confirmedReservations", confirmedReservations);
        return "confirmed_users_page";
    }

}
