package bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("transactions")
public class TransactionController {

    @GetMapping("")
    public String index() {
        return "index";
    }
}
