package bank.controllers;

import bank.data.UserRepository;
import bank.models.User;
import bank.models.dto.LoginFormDTO;
import bank.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    private static final String sessionKey = "user"; // Key used to store IDs

    // Looks for data with the key "user" (sessionKey) in the user's session. (Allows handlers to manage authentication)
    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(sessionKey);

        if (userId == null) { // If no user ID is in the session, returns null.
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) { // If no user with the given ID, returns null.
            return null;
        }
        return user.get(); // If found, it attempts to retrieve user from the database.
    }

    // Uses HttpSession to store key:value pair. (Allows handlers to manage authentication)
    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(sessionKey, user.getId());
    }

    @GetMapping("register")
    public String displayRegisterForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        return "/register";
    }


    @PostMapping("register") // HttpServletRequest represents the incoming request.
    public String processRegisterFrom(@ModelAttribute @Valid RegisterFormDTO registerFormDTO, Errors errors,
                                      Model model, HttpServletRequest request) {

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());
        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Registration Failed!");
            return "register";
        }

        if (existingUser != null) {
            errors.reject("Registered user", "Username already exists.");
            return "register";
        }

        if (!password.equals(verifyPassword)) { // (!Objects.equals(password, verifyPassword) also works here.
            errors.reject("Non-matching passwords", "Passwords do not match");
            return "register";
        }

        userRepository.save(newUser); // Store new user in the database.
        setUserInSession(request.getSession(), newUser); // Creates a new session for user.
        return "redirect:";
    }

    @GetMapping("login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        return "/login";
    }

    @PostMapping("login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors, Model model) {
        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In Failed!");
            return "login";
        }

        if (theUser == null) {
            errors.reject("Invalid user", "User does not exist.");
            return "login";
        }

        if (theUser.checkPassword(loginFormDTO.getPassword())) {
            errors.reject("Invalid password", "Password is incorrect.");
            return "login";
        }

        return "redirect:";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
