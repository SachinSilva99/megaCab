package org.example.demo1.repository.repo.impl;

import org.example.demo1.repository.entity.Customer;
import org.example.demo1.repository.repo.CustomerRepository;
import org.example.demo1.repository.repo.crud.CrudRepoImpl;

import java.sql.Connection;
import java.util.Optional;

public class CustomerRepoImpl extends CrudRepoImpl implements CustomerRepository {
    @Override
    public Optional<Customer> findByNic(Connection connection, String nic) {
        try {
            Customer customer = executeQuery("SELECT * FROM customers WHERE nic = ?", connection, Customer.class, nic);
            if (customer != null) {
                return Optional.of(customer);
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
