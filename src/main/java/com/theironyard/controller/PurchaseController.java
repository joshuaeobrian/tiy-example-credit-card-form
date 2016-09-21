package com.theironyard.controller;

import com.theironyard.entity.Customer;
import com.theironyard.repository.CustomerRepository;
import com.theironyard.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

// this is a Spring MVC controller
@Controller
public class PurchaseController {

    // the CustomerRepository and TransactionService are autowired
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionService transactionService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String promptForCreditCardData(Model model){

        // create a new customer to bind to the credit card form
        model.addAttribute("customer", new Customer());

        // show the credit card farm
        return "creditCardForm";
    }

    @RequestMapping(path = "/purchase", method = RequestMethod.POST)
    public String processTransaction(
            // The customer, addresses, and credit card data are automatically
            // assembled into the Customer entity. The @Valid annotation forces
            // Spring to validate the entity based on its annotations.
            @Valid Customer customer,
            // The results of validating the customer are provided by the bindingResult
            BindingResult bindingResult,
            Model model){

        // do NOT we have errors?
        if(!bindingResult.hasErrors()){

            // process our transaction
            TransactionService.TransactionResult result = transactionService.processTransaction(customer.getCreditCard());

            // did the transaction fail?
            if(!result.success){

                // add the failure to our binding results
                FieldError fieldError = new FieldError("customer", "creditCard.number", customer.getCreditCard().getNumber(), false, new String[]{"Declined.customer.creditCard.number"}, (String[])null, result.message);
                bindingResult.addError(fieldError);
            }
        }

        if(bindingResult.hasErrors()){
            // there were were form errors or the card was declined

            // add the binding results into the model
            model.addAttribute("bindingResult", bindingResult);

            // show the credit card form again
            return "creditCardForm";

        } else {

            // the transaction was successful
            customerRepository.save(customer);

            // redirect to the success page
            return "redirect:/success";
        }

    }

    @RequestMapping(path = "/success", method = RequestMethod.GET)
    public String success(){
        // show the success page
        return "success";
    }

}
