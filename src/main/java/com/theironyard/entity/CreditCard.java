package com.theironyard.entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue
    private Long id;

    @Transient // don't persist this field.
    @NotEmpty // the field can't be an empty string
    @CreditCardNumber // the field has to be a correctly formatted credit card number
                      // Note: this doesn't process a transaction
    private String number;

    // don't persist this either!
    @Transient
    @Pattern(regexp = "^[0-9]{3}$") // the field must be three numbers from 0 to 9
    private String cvv;

    @NotNull
    @Pattern(regexp = "^[0-9]{2}$") // the field must be two numbers from 0 to 9
    private String expirationMonth;

    @NotNull
    @Pattern(regexp = "^20[0-9]{2}$") // the field must start with "20" and be followed by two numbers from 0 to 9
    private String expirationYear;

    // We don't want to store the credit card number, but we can store the last
    // four digits. This property is automatically set when the card number is set.
    @NotNull
    private String lastFourDigits;

    // When we set the CC number also set the last four digits
    public void setNumber(String number){
        this.number = number;

        // set the last four digits too
        if(number.length() == 16) {
            this.lastFourDigits = number.substring(11, 15);
        } else {
            this.lastFourDigits = null;
        }
    }

    public String getNumber(){
        return this.number;
    }

    // getter for the last four digits of the card
    public String getLastFourDigits(){
        return this.lastFourDigits;
    }

    // other getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }
}
