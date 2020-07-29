package org.example.service;


import org.example.entity.Account;
import org.example.repos.AccountRepository;
import org.example.service.imp.AccountServiceIml;
import org.example.service.imp.ServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("accountService")
@Transactional(isolation = Isolation.SERIALIZABLE)
public class AccountService implements ServiceIml<Account>, AccountServiceIml<Account> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account obj) {
        return accountRepository.save(obj);
    }

    @Override
    public void delete(Account obj) {
        accountRepository.delete(obj);
    }

    @Override
    public void deleteAll(List<Account> ent) {
        accountRepository.deleteAll(ent);
    }

    @Override
    public void saveAll(List<Account> ent) {
        accountRepository.saveAll(ent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Account> findAll(int page, int limit) {
        return accountRepository.findAll(PageRequest.of(page, limit));
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getById(Long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountByNumber(String number) {
        return accountRepository.getAccountByNumber(number);
    }
}