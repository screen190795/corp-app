package ru.vivt.corpapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vivt.corpapp.entity.MyUserDetails;
import ru.vivt.corpapp.repository.UserRepository;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = {"/homepage"}, method = RequestMethod.GET)
    public String login(@AuthenticationPrincipal MyUserDetails user, Model model) {
        model.addAttribute("user", user.getUsername());
        model.addAttribute("role", user.getAuthorities());
        return "homepage";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping(value = "/createIncident")
    public String createIncident() {
        return "createIncident";
    }

    @RequestMapping(value = "/logout-success")
    public String logout() {
        return "logout";
    }

}
