package com.parth.crm.repository;

import com.parth.crm.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {
}
