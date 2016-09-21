package com.theironyard.repository;

import com.theironyard.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

// this is just a standard JpaRepository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
