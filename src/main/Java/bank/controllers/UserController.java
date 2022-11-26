package bank.controllers;

import bank.data.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    UserRepository userRepository;

    @GetMapping("profile")
    public String displayProfile(Model model) {
        return "/user/profile";
    }

    //TODO: Add delete user handlers
    @GetMapping("delete")
    public String deleteUser(Model model) {

        return "";
    }

    @PostMapping("delete")
    public String processDeleteUser(Model model, Integer userId) {

        return "";
    }
}
