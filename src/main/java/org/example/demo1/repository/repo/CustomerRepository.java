package org.example.demo1.repository.repo;

import org.example.demo1.repository.entity.Customer;

import java.sql.Connection;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findByNic(Connection connection, String nic);
}
