package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.dto.request.CustomerDtoRequest;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;
import org.example.dto.response.CustomerDtoResponse;
import org.example.dto.response.groups.FullUser;
import org.example.dto.response.groups.ListUser;
import org.example.facade.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api0/customer")
public class CustomerController {
    @Autowired
    CustomerFacade customerFacade;

    @GetMapping("/{id}")
    @JsonView({FullUser.class})
    public CustomerDtoResponse getCustomerById(@PathVariable(value = "id") Long id) {
        return customerFacade.getById(id);
    }

    @GetMapping("")
    @JsonView({ListUser.class})
    public Page<CustomerDtoResponse> getAllCustomers(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return customerFacade.findAll(page, limit);
    }

    @PostMapping(path = "")
    public CustomerDtoResponse createCustomer(@Validated(New.class) @RequestBody CustomerDtoRequest customer) {
        return customerFacade.save(customer);
    }

    @PutMapping(path = "")
    public CustomerDtoResponse updateCustomer(@Validated(Update.class) @RequestBody CustomerDtoRequest customer) {
        return customerFacade.update(customer);
    }

    @DeleteMapping("")
    public void deleteCustomer(@RequestParam(value = "id") Long customerId) {
        customerFacade.deleteById(customerId);
    }
}
