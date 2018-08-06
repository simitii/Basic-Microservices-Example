package com.demo.microservices.oneservice.repository;

import java.util.List;

import com.demo.microservices.oneservice.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}