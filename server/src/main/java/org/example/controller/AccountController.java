package org.example.controller;

import org.example.dto.request.AccountDtoRequest;
import org.example.dto.request.TransferDtoRequest;
import org.example.dto.request.groups.AdminTransfer;
import org.example.dto.request.groups.FromToTransfer;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;
import org.example.dto.response.AccountDtoResponse;
import org.example.facade.AccountFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api0/account")
public class AccountController {
    @Autowired
    AccountFacade accountFacade;

    @GetMapping("/{id}")
    public AccountDtoResponse getAccountById(@PathVariable(value = "id") Long id) {
        return accountFacade.getById(id);
    }

    @GetMapping("/number/{number}")
    public AccountDtoResponse getAccountByNumber(@PathVariable(value = "number") String number) {
        return accountFacade.getAccountByNumber(number);
    }

    @GetMapping("")
    public Page<AccountDtoResponse> getAllAccount(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return accountFacade.findAll(page, limit);
    }

    @PostMapping("")
    public AccountDtoResponse createAccount(@Validated(New.class) @RequestBody AccountDtoRequest account) {
        return accountFacade.save(account);
    }

    @PutMapping("")
    public AccountDtoResponse updateAccount(@Validated(Update.class) @RequestBody AccountDtoRequest account) {
        return accountFacade.update(account);
    }

    @DeleteMapping("")
    public void deleteAccount(@RequestParam(value = "id") Long accountId) {
        accountFacade.deleteById(accountId);
    }

    @PutMapping("topup")
    public AccountDtoResponse topUpAccount(
            @Validated(AdminTransfer.class) @RequestBody TransferDtoRequest transferDtoRequest
    ) {
        return accountFacade.topUpAccount(transferDtoRequest.getToNum(), transferDtoRequest.getSum());
    }

    @PutMapping("withdraw")
    public AccountDtoResponse withdrawFromAccount(
            @Validated(AdminTransfer.class) @RequestBody TransferDtoRequest transferDtoRequest
    ) {
        return accountFacade.withdrawFromAccount(transferDtoRequest.getToNum(), transferDtoRequest.getSum());
    }

    @PutMapping("transfer")
    public void transfer(
            @Validated(FromToTransfer.class) @RequestBody TransferDtoRequest transferDtoRequest
    ) {
        accountFacade.transfer(transferDtoRequest.getFromNum(), transferDtoRequest.getToNum(), transferDtoRequest.getSum());
    }




}
