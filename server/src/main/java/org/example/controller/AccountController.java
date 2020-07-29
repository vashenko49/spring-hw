package org.example.controller;

import org.example.dto.response.AccountDtoResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api0/account")
public class AccountController {

    @PutMapping("/add")
    public AccountDtoResponse topUpAccount() {
        return null;
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
