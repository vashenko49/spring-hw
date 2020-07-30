package org.example.repos;

import org.example.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
