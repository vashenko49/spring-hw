package org.example.service;

import org.example.entity.Account;
import org.example.entity.enums.Currency;
import org.example.repos.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@SpringBootTest
public class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    public void testExecutionSaveAccount() {
        Account account = new Account("1", Currency.CHF, 2342.2, null);
        given(accountRepository.save(any())).willReturn(account);

        Account saved = accountService.save(account);

        assertThat(saved).isEqualTo(account);
    }

    @Test
    public void testExecutionUpdateAccount() {
        Account account = new Account("1", Currency.CHF, 2342.2, null);
        given(accountRepository.save(any())).willReturn(account);
        given(accountRepository.getAccountByNumber(anyString())).willReturn(account);

        Account saved = accountService.update(account);

        assertThat(saved).isEqualTo(account);
    }

    @Test
    public void topUpAccountTest() {
        //given
        double sum = 1234.0;
        double balance = 2342.2;
        Account account = new Account("1", Currency.CHF, balance, null);
        when(accountService.save(any())).then(returnsFirstArg());
        given(accountRepository.getAccountByNumber(anyString())).willReturn(account);
        //when
        Account result = accountService.topUpAccount("1", sum);
        //than
        assertThat(result.getBalance()).isEqualTo(balance + sum);
    }

    @Test
    public void withdrawFromAccountTest() {
        //given
        double sum = 1234.0;
        double balance = 2342.2;
        Account account = new Account("1", Currency.CHF, balance, null);
        when(accountService.save(any())).then(returnsFirstArg());
        given(accountRepository.getAccountByNumber(anyString())).willReturn(account);
        //when
        Account result = accountService.withdrawFromAccount("1", sum);
        //than
        assertThat(result.getBalance()).isEqualTo(balance - sum);
    }

}