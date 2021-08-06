package com.vantalii.data.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vantalii.data.mongodb.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Customer findByFirstName(String firstName);

	public List<Customer> findByLastName(String lastName);

}
