package bank.controllers;

import bank.data.UserRepository;
import bank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping("profile")
    public String displayProfile(Model model, HttpServletRequest request) {
        User theUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("name", theUser.getUsername());
        model.addAttribute("balance", theUser.getBalance());
        return "/user/profile";
    }

    @PostMapping("profile")
    public String processProfileForm(Model model, HttpServletRequest request) {
        User theUser = authenticationController.getUserFromSession(request.getSession());
//        model.addAttribute("addFunds", theUser.setBalance());
        return "user/profile";
    }

    //TODO: Add delete user handlers
    @GetMapping("delete")
    public String deleteUser(Model model) {
        model.addAttribute("title", "Oh no! It's sad to see you go!");
        return "/user/delete";
    }

    @PostMapping("delete")
    public String processDeleteUser(Model model, HttpServletRequest request, Errors errors) {
        User theUser = authenticationController.getUserFromSession(request.getSession());

        if (errors.hasErrors()) {
            model.addAttribute("title", "Something went wrong, try again.");
            return "/user/delete";
        }
        // TODO: Need to make sure the correct password is entered so everybody cant just delete your account.
        if (theUser != null) {
            userRepository.deleteById(theUser.getId()); //Deletes existing user from database.
            request.getSession().invalidate();  //Ends user session.
        }
        return "redirect:/login";
    }
}
