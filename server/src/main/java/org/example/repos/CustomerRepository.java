package org.example.repos;

import org.example.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Optional<Customer> findByEmail(String aLong);
}
