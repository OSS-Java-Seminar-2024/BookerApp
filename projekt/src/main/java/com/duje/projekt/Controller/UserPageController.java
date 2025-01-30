package com.duje.projekt.Controller;

import com.duje.projekt.Service.TripService;
import com.duje.projekt.Service.UserService;
import com.duje.projekt.model.TripModel;
import com.duje.projekt.model.UserModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

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
}
