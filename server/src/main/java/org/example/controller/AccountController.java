package org.example.controller;

import org.example.dto.request.AccountDtoRequest;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;
import org.example.dto.response.AccountDtoResponse;
import org.example.facade.AccountFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api0/account")
public class AccountController {
    @Autowired
    AccountFacade accountFacade;

    @PostMapping("")
    public AccountDtoResponse createAccount(@Validated(New.class) @RequestBody AccountDtoRequest account) {
        return accountFacade.save(account);
    }

    @PutMapping("")
    public AccountDtoResponse updateAccount(@Validated(Update.class) @RequestBody AccountDtoRequest account) {
        return accountFacade.update(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable(value = "id") Long accountId) {
        accountFacade.deleteById(accountId);
    }

    @PutMapping("/add")
    public AccountDtoResponse topUpAccount(
            @RequestBody String number,
            @RequestBody double sum
    ) {
        return accountFacade.topUpAccount(number, sum);
    }

    @PutMapping("/withdraw")
    public AccountDtoResponse withdrawFromAccount() {
        return null;
    }

    @PutMapping("/transfer")
    public AccountDtoResponse transfer() {
        return null;
    }


}
