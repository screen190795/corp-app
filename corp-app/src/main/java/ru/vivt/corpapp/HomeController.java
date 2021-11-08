package ru.vivt.corpapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vivt.corpapp.models.MyUserDetails;
import ru.vivt.corpapp.repositories.UserRepository;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(@AuthenticationPrincipal MyUserDetails user, Model model) {
        model.addAttribute("user", user.getUsername());
        return "adminMain";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal MyUserDetails user, Model model) {
        model.addAttribute("user", user.getUsername());
        return "userMain";
    }

    @GetMapping("/staff")
    public String staff(@AuthenticationPrincipal MyUserDetails user, Model model) {
        model.addAttribute("user", user.getUsername());
        return "staffMain";
    }

    @GetMapping("/staff/incidents")
    public String manageIncidents(@AuthenticationPrincipal MyUserDetails user, Model model) {
        model.addAttribute("user", user.getUsername());
        return "incidents";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/logout-success")
    public String logout() {
        return "logout";
    }

}
