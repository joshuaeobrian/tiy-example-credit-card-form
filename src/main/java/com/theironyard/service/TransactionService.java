package com.theironyard.service;

import com.theironyard.entity.CreditCard;
import org.springframework.stereotype.Service;

// This is a fake service that pretends to process credit card transactions.
// Only the number 4111111111111111 works.
@Service
public class TransactionService {

    public TransactionResult processTransaction(CreditCard creditCard){
        if(creditCard.getNumber().equals("4111111111111111")){
            return new TransactionResult(true, "Success");
        } else {
            return new TransactionResult(false, "Declined");
        }
    }

    public class TransactionResult {
        public boolean success;
        public String message;

        public TransactionResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }
}
