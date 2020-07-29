package org.example.facade;

import org.example.dto.response.AccountDtoResponse;
import org.example.entity.Account;
import org.example.service.AccountService;
import org.example.service.imp.AccountServiceIml;
import org.example.service.imp.ServiceIml;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class AccountFacade implements ServiceIml<AccountDtoResponse>, AccountServiceIml<AccountDtoResponse> {

    @Autowired
    AccountService accountService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public AccountDtoResponse getAccountByNumber(String number) {
        return mapper.map(accountService.getAccountByNumber(number), AccountDtoResponse.class);
    }

    @Override
    public AccountDtoResponse save(AccountDtoResponse obj) {
        return mapper.map(accountService.save(mapper.map(obj, Account.class)), AccountDtoResponse.class);
    }

    @Override
    public void delete(AccountDtoResponse obj) {
        accountService.delete(mapper.map(obj, Account.class));
    }

    @Override
    public void deleteAll(List<AccountDtoResponse> ent) {
        accountService.deleteAll(ent.stream().map(accountDto -> mapper.map(accountDto, Account.class)).collect(Collectors.toList()));
    }

    @Override
    public void saveAll(List<AccountDtoResponse> ent) {
        accountService.saveAll(ent.stream().map(accountDto -> mapper.map(accountDto, Account.class)).collect(Collectors.toList()));
    }

    @Override
    public Page<AccountDtoResponse> findAll(int page, int limit) {
        return null;
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
