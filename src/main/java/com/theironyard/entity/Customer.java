package com.theironyard.entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Map;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Length(min = 2, max = 100) // the string must be between 2 and 100 characters long
    private String firstName;

    @NotNull
    @Length(min = 2, max = 100)
    private String lastName;

    @NotNull
    @NotEmpty // the email can't be an empty string
    @Email // must be a correctly formatted email address
    private String email;

    @NotNull
    @Valid // makes Spring validation also validate the addresses
    @OneToMany(cascade = CascadeType.ALL) // CascadeType.ALL makes JPA save the addresses when the customer is saved
    private Map<String, Address> addresses;

    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL) // CascadeType.ALL makes JPA save the credit card when the customer is saved
    private CreditCard creditCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Map<String, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<String, Address> addresses) {
        this.addresses = addresses;
    }
}
