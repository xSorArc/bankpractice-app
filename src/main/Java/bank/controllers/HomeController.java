package bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //Needs index()
    //      login()
    //      logout()
    //      register()
    //

    @GetMapping("")
    public String index() {
        return "index";
    }
}
