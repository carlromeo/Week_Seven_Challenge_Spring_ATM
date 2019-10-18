package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("customers", customerRepository.findAll());

        return "index";
    }

    @GetMapping("/newcustomer")
    public String newCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "newcustomerform";
    }

    @PostMapping("/newcustomer")
    public String processNewCust(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "newcustomerform";
        }
        customerRepository.save(customer);
        return "redirect:/";
    }

    @GetMapping("/newaccount/{id}")
    public String addNewAccount(@PathVariable("id") long id, Model model) {

        model.addAttribute("customer", customerRepository.findById(id));
        Account account = new Account();
        model.addAttribute("account", account);
        return "newaccountform";
    }

    @PostMapping("/processaccount/{id}")
    public String processAccount(@PathVariable("id") long id, @Valid Account account, BindingResult result) {
        if (result.hasErrors()) {
            return "newaccountform";
        }

        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String showTransactions(@PathVariable("id") long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id));
        return "showtransactions";
    }

    @GetMapping("/transaction/{id}")
    public String trasaction (@PathVariable("id") long id, Model model) {
        Account account = accountRepository.findById(id);
        model.addAttribute("account", account);
        return "login";
    }

    @PostMapping("/login/{id}")
    public String login(@PathVariable("id") long id,
                                  @Valid Account account, Model model,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }



    }
        @GetMapping("/statement/{id}")
        public String receipt (@PathVariable("id") long id, Model model) {
            Account accountz = accountRepository.findById(id);

            Set<Transaction> transactions = transactionRepository.findAllByaccountNumber(accountz.getAccountNumber());
            model.addAttribute("transactions", transactions);
            return "receipt";
        }




    }













