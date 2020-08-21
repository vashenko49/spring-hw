package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.CustomerDtoRequest;
import org.example.dto.request.SignIn;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;
import org.example.dto.response.AuthResponse;
import org.example.dto.response.CustomerDtoResponse;
import org.example.dto.response.groups.FullUser;
import org.example.dto.response.groups.ListUser;
import org.example.exception.BadRequestException;
import org.example.exception.CustomerNotFound;
import org.example.facade.CustomerFacade;
import org.example.security.oauth2.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api0/customer")
public class CustomerController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    CustomerFacade customerFacade;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    @JsonView({FullUser.class})
    public CustomerDtoResponse getCustomerById(@PathVariable(value = "id") Long id) throws CustomerNotFound {
        log.info("Find customer by {} id", id.toString());
        return customerFacade.getById(id);
    }

    @GetMapping("")
    @JsonView({ListUser.class})
    public Page<CustomerDtoResponse> getAllCustomers(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        log.info("Find all customer");
        return customerFacade.findAll(page, limit);
    }

    @PostMapping(path = "")
    public CustomerDtoResponse createCustomer(@Validated(New.class) @RequestBody CustomerDtoRequest customer) {
        log.info("create a new customer");
        return customerFacade.save(customer);
    }

    @PutMapping(path = "")
    public CustomerDtoResponse updateCustomer(@Validated(Update.class) @RequestBody CustomerDtoRequest customer) {
        log.info("update customer");
        return customerFacade.update(customer);
    }

    @DeleteMapping("")
    public void deleteCustomer(@RequestParam(value = "id") Long customerId) {
        log.info("delete customer");
        customerFacade.deleteById(customerId);
    }

    @PostMapping("/authenticate")
    private ResponseEntity<?> logIn(@Validated @RequestBody SignIn signIn) throws Exception {
        log.info("log in");
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword())
        );


        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = tokenProvider.createToken(authenticate);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public CustomerDtoResponse registerUser(@Valid @RequestBody CustomerDtoRequest user) {
        log.info("signup");
        if (customerFacade.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        CustomerDtoResponse save = customerFacade.save(user);
        return save;
    }


    /*
     * Return 200 status when token is valid or 401
     * */
    @GetMapping("/token-active")
    public void checkingForTokenActivity() {
        log.info("check token");
    }
}
