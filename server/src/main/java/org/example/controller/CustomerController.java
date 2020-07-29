package org.example.controller;

import org.example.dto.response.AccountDtoResponse;
import org.example.dto.response.CustomerDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api0/customer")
public class CustomerController {
    /*
     * Get customer by id with account
     * */
    @GetMapping("/{id}")
    public CustomerDtoResponse getCustomerById(@PathVariable(value = "id") Long id) {
        return null;
    }

    @GetMapping("")
    public Page<CustomerDtoResponse> getAllCustomers() {
        return null;
    }

    @PostMapping("")
    public CustomerDtoResponse createCustomer() {
        return null;
    }

    @PutMapping("")
    public CustomerDtoResponse updateCustomer() {
        return null;
    }

    @DeleteMapping("")
    public void deleteCustomer() {

    }

    @PostMapping("/account")
    public AccountDtoResponse createAccount() {
        return null;
    }

    @DeleteMapping("/account")
    public void deleteAccount() {

    }


}
