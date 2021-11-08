package ru.vivt.corpapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vivt.corpapp.models.MyUserDetails;
import ru.vivt.corpapp.repositories.UserRepository;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = { "/user" }, method = RequestMethod.GET)
    public String user(@AuthenticationPrincipal MyUserDetails user, Model model){
        System.out.println(user.getUsername() + " : " + user.getPassword());
        model.addAttribute("user",user.getUsername());
        return "home";
    }

    @GetMapping("/admin")
    public String admin(){
        return "home";
    }

    @RequestMapping(value = "/logout-success", produces = MediaType.TEXT_HTML_VALUE)
    public String logout(){
        return "logout";
    }

}
