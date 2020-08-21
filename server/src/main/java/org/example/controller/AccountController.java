package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.AccountDtoRequest;
import org.example.dto.request.TransferDtoRequest;
import org.example.dto.request.groups.AdminTransfer;
import org.example.dto.request.groups.FromToTransfer;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;
import org.example.dto.response.AccountDtoResponse;
import org.example.exception.CustomerNotFound;
import org.example.facade.AccountFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api0/account")
public class AccountController {

    @Autowired
    AccountFacade accountFacade;

    @GetMapping("/{id}")
    public AccountDtoResponse getAccountById(@PathVariable(value = "id") Long id) {
        log.info("Find account by {} id", id.toString());
        return accountFacade.getById(id);
    }

    @GetMapping("/number/{number}")
    public AccountDtoResponse getAccountByNumber(@PathVariable(value = "number") String number) {
        log.info("Find account by {} number", number);
        return accountFacade.getAccountByNumber(number);
    }

    @GetMapping("")
    public Page<AccountDtoResponse> getAllAccount(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        log.info("get all accounts");
        return accountFacade.findAll(page, limit);
    }

    @PostMapping("")
    public AccountDtoResponse createAccount(@Validated(New.class) @RequestBody AccountDtoRequest account) throws CustomerNotFound {
        log.info("Create a new account");
        return accountFacade.save(account);
    }

    @PutMapping("")
    public AccountDtoResponse updateAccount(@Validated(Update.class) @RequestBody AccountDtoRequest account) {
        log.info("Update account by {} id", account.getId().toString());
        return accountFacade.update(account);
    }

    @DeleteMapping("")
    public void deleteAccount(@RequestParam(value = "id") Long accountId) {
        log.info("Delete account by {} id", accountId.toString());
        accountFacade.deleteById(accountId);
    }

    @PutMapping("topup")
    public AccountDtoResponse topUpAccount(
            @Validated(AdminTransfer.class) @RequestBody TransferDtoRequest transferDtoRequest
    ) {
        log.info("Top up account by {} number to {}", transferDtoRequest.getToNum(), transferDtoRequest.getSum());
        return accountFacade.topUpAccount(transferDtoRequest.getToNum(), transferDtoRequest.getSum());
    }

    @PutMapping("withdraw")
    public AccountDtoResponse withdrawFromAccount(
            @Validated(AdminTransfer.class) @RequestBody TransferDtoRequest transferDtoRequest
    ) {
        log.info("With draw account by {} number to {}", transferDtoRequest.getToNum(), transferDtoRequest.getSum());
        return accountFacade.withdrawFromAccount(transferDtoRequest.getToNum(), transferDtoRequest.getSum());
    }

    @PutMapping("transfer")
    public void transfer(
            @Validated(FromToTransfer.class) @RequestBody TransferDtoRequest transferDtoRequest
    ) {
        log.info("Transfer {} sum from {} to {}", transferDtoRequest.getSum(), transferDtoRequest.getFromNum(), transferDtoRequest.getToNum());
        accountFacade.transfer(transferDtoRequest.getFromNum(), transferDtoRequest.getToNum(), transferDtoRequest.getSum());
    }


}
