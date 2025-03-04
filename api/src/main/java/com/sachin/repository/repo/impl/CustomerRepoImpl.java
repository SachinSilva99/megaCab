package com.sachin.repository.repo.impl;

import jakarta.enterprise.context.ApplicationScoped;
import com.sachin.entity.Customer;
import com.sachin.repository.repo.CustomerRepository;

import java.sql.Connection;
import java.util.Optional;

@ApplicationScoped
public class CustomerRepoImpl extends CrudRepoImpl implements CustomerRepository {
    @Override
    public Optional<Customer> findByNic(Connection connection, String nic) {
        try {
            Customer customer = executeQuery("SELECT * FROM customer WHERE nic = ?", connection, Customer.class, nic);
            if (customer != null) {
                return Optional.of(customer);
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
