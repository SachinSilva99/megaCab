package org.example.demo1.repository.repo;

import org.example.demo1.repository.SuperRepository;
import org.example.demo1.repository.entity.Customer;
import org.example.demo1.repository.repo.crud.CrudRepo;

import java.sql.Connection;
import java.util.Optional;

public interface CustomerRepository extends CrudRepo {
    Optional<Customer> findByNic(Connection connection, String nic);
}
