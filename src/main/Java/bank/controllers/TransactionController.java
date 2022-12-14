package bank.controllers;

import bank.data.TransactionRepository;
import bank.data.UserRepository;
import bank.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "All Transactions");
        model.addAttribute("transactions", transactionRepository.findAll());
        return "transactions/index";
    }

    @GetMapping("add")
    public String addTransaction(Model model) {
        model.addAttribute("title", "Add Transaction");
        model.addAttribute(new Transaction());
        return "transactions/add";
    }

    @PostMapping("add")
    public String processAddTransaction(@ModelAttribute @Valid Transaction newTransaction, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Transaction");
            return "transactions/add";
        }
        transactionRepository.save(newTransaction);
        return "redirect:/transactions";
    }

    @GetMapping("delete")
    public String displayDeleteTransaction(Model model) {
        model.addAttribute("title", "Delete Form");
        model.addAttribute("transactions", transactionRepository.findAll());
        return "transactions/index";
    }

    // TODO: Add processDelete()
    @PostMapping("delete")
    public String processDeleteTransactionForm(@RequestParam(required = false) int[] transactionIds) {
//        showMessageDialog(null, "Are you sure you want to delete this?");
        if (transactionIds != null) {
            for (int id : transactionIds) {
                transactionRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

//    @GetMapping("view/{transaction_id}")
//    public String displayViewTransaction(Model model, @PathVariable int transaction_id) {
//        //Might need Optional object.
//        model.addAttribute("transaction", transactionRepository.findById(transaction_id));
//        return "transactions/view";
//    }
}
