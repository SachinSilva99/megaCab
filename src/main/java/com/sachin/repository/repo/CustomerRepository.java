package com.sachin.repository.repo;

import com.sachin.entity.Customer;

import java.sql.Connection;
import java.util.Optional;

public interface CustomerRepository extends CrudRepo {
    Optional<Customer> findByNic(Connection connection, String nic);
}
