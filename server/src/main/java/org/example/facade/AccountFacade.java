package org.example.facade;

import org.example.dto.request.AccountDtoRequest;
import org.example.dto.response.AccountDtoResponse;
import org.example.entity.Account;
import org.example.entity.Customer;
import org.example.service.AccountService;
import org.example.service.CustomerService;
import org.example.service.imp.AccountServiceIml;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountFacade implements FacadeIml<AccountDtoRequest, AccountDtoResponse>, AccountServiceIml<AccountDtoResponse> {

    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public AccountDtoResponse getAccountByNumber(String number) {
        return mapper.map(accountService.getAccountByNumber(number), AccountDtoResponse.class);
    }

    @Override
    public AccountDtoResponse topUpAccount(String number, double sum) {
        return mapper.map(accountService.topUpAccount(number, sum), AccountDtoResponse.class);
    }

    @Override
    public AccountDtoResponse withdrawFromAccount(String number, double sum) {
        return mapper.map(accountService.withdrawFromAccount(number, sum), AccountDtoResponse.class);
    }

    @Override
    public void transfer(String fromNum, String toNum, double sum) {
        accountService.transfer(fromNum, toNum, sum);
    }

    @Override
    public AccountDtoResponse save(AccountDtoRequest obj) {
        Customer byId = customerService.getById(obj.getCustomer());
        Account map = mapper.map(obj, Account.class);
        map.setCustomer(byId);
        return mapper.map(accountService.save(map), AccountDtoResponse.class);
    }

    @Override
    public AccountDtoResponse update(AccountDtoRequest obj) {
        return mapper.map(accountService.update(mapper.map(obj, Account.class)), AccountDtoResponse.class);
    }

    @Override
    public void delete(AccountDtoRequest obj) {
        accountService.delete(mapper.map(obj, Account.class));
    }

    @Override
    public void deleteAll(List<AccountDtoRequest> ent) {
        accountService.deleteAll(ent.stream().map(accountDto -> mapper.map(accountDto, Account.class)).collect(Collectors.toList()));
    }

    @Override
    public void saveAll(List<AccountDtoRequest> ent) {
        accountService.saveAll(ent.stream().map(accountDto -> mapper.map(accountDto, Account.class)).collect(Collectors.toList()));
    }

    @Override
    public Page<AccountDtoResponse> findAll(int page, int limit) {
        return accountService.findAll(page, limit).map(account -> mapper.map(account, AccountDtoResponse.class));
    }

    @Override
    public void deleteById(Long id) {
        accountService.deleteById(id);
    }

    @Override
    public AccountDtoResponse getById(Long id) {
        return mapper.map(accountService.getById(id), AccountDtoResponse.class);
    }
}
