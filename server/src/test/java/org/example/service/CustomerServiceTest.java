package org.example.service;

import org.example.entity.Customer;
import org.example.repos.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    void save() {
        Customer custome = Customer.builder()
                .accounts(null)
                .age(11)
                .email("test@test.test")
                .employers(null)
                .name("test")
                .password("passwrf")
                .phone("234")
                .build();

        given(customerRepository.save(any())).willReturn(custome);
        Customer save = customerService.save(custome);

        assertThat(save).isEqualTo(custome);
    }

    @Test
    void saveAll() {
        Customer custome = Customer.builder()
                .accounts(null)
                .age(11)
                .email("test@test.test")
                .employers(null)
                .name("test")
                .password("passwrf")
                .phone("234")
                .build();

        given(customerRepository.save(any())).willReturn(custome);
        Customer save = customerService.save(custome);

        assertThat(save).isEqualTo(custome);
    }

}