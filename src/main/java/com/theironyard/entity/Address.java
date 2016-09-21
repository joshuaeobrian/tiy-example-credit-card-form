package com.theironyard.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull // This field can not be null
    @NotEmpty // It can't be blank
    @Length(max = 50) // It must be 50 or less characters long
    private String line1;

    @Length(max = 50)
    private String line2;

    @NotNull
    @NotEmpty
    @Length(max = 50)
    private String city;

    @NotNull
    @Length(min = 2, max = 2) // The state must be exactly two characters long
    private String state;

    @NotNull
    @Length(min = 5, max = 10) // The postalCode must be from 5 to 10 characters long
    private String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
