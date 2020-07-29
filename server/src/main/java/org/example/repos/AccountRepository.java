package org.example.repos;

import org.example.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    Account getAccountByNumber(String number);
}
