package com.production.ehayvanbackendapi.Services;

import com.production.ehayvanbackendapi.DTO.CustomerDTO;
import com.production.ehayvanbackendapi.Entities.Customer;
import com.production.ehayvanbackendapi.Mappers.CustomerMapper;
import com.production.ehayvanbackendapi.Repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer != null ? customerMapper.convertToDto(customer) : null;
    }

//    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
//        Customer customer = customerMapper.convertToEntity(customerDTO);
//        Customer savedCustomer = customerRepository.save(customer);
//        return customerMapper.convertToDto(savedCustomer);
//    }

    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> customerDtoList = new ArrayList<>();
        for(Customer cust: customerList){
            customerDtoList.add(customerMapper.convertToDto(cust));
        }
        return customerDtoList;
    }
    // Other service methods for creating, updating, and deleting customers
}

