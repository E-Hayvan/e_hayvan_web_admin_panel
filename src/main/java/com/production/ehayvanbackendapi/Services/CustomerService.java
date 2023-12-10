package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.Entities.Customer;
import com.production.ehayvanbackendapi.Repositories.CustomerRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

}
