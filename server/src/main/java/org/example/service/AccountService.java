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
    public Account update(Account obj) {
        Account accountByNumber = accountRepository.getAccountByNumber(obj.getNumber());
        accountByNumber.setBalance(obj.getBalance());
        accountByNumber.setCurrency(obj.getCurrency());
        return accountRepository.save(accountByNumber);
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

    @Override
    public Account topUpAccount(String number, double sum) {
        Account accountByNumber = accountRepository.getAccountByNumber(number);
        accountByNumber.setBalance(accountByNumber.getBalance() + sum);
        return accountRepository.save(accountByNumber);
    }

    @Override
    public Account withdrawFromAccount(String number, double sum) {
        Account accountByNumber = accountRepository.getAccountByNumber(number);
        if (accountByNumber.getBalance() >= sum) {
            accountByNumber.setBalance(accountByNumber.getBalance() - sum);
        }
        return accountRepository.save(accountByNumber);
    }

    @Override
    public void transfer(String fromNum, String toNum, double sum) {
        Account accountFrom = accountRepository.getAccountByNumber(fromNum);
        Account accountTo = accountRepository.getAccountByNumber(toNum);
        if (accountFrom.getBalance() >= sum) {

            /*
            * Процесс конвертации валют
            * */
            double sumTo = sum;
            if(!accountFrom.getCurrency().equals(accountTo.getCurrency())){
                sumTo = accountFrom.getCurrency().currencyConversion(sum, accountTo.getCurrency());
            }

            accountTo.setBalance(accountTo.getBalance() + sumTo);
            accountFrom.setBalance(accountFrom.getBalance() - sum);
        }
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
    }
}
