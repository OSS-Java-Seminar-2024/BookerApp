package com.duje.projekt.Controller;


import com.duje.projekt.Service.UserService;
import com.duje.projekt.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserCotroller {

    @Autowired
    private UserService userService;


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
        UserModel registeredUser= userService.registerUser(user.getUsername(),user.getPassword(),user.getEmail());
        return registeredUser == null ?  "error_page" :  "redirect:/login";
    }

}
