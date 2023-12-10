package com.production.ehayvanbackendapi.Repositories;

import com.production.ehayvanbackendapi.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
