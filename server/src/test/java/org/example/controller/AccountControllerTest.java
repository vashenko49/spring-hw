package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.response.AccountDtoResponse;
import org.example.entity.enums.Currency;
import org.example.facade.AccountFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AccountFacade accountFacade;

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    void getAccountByIdTest() throws Exception {
        AccountDtoResponse build = AccountDtoResponse.builder().balance(1234.0).currency(Currency.CHF).number("1234").id(1L).build();
        given(accountFacade.getById(anyLong())).willReturn(build);

        MvcResult mvcResult = mockMvc.perform(get("/api0/account/{id}", 1L)).andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertThat(contentAsString).contains(build.getNumber(), Double.toString(build.getBalance()), build.getCurrency().toString());
    }

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    void getAccountByNumber() throws Exception {
        AccountDtoResponse build = AccountDtoResponse.builder().balance(1234.0).currency(Currency.CHF).number("1234").id(1L).build();
        given(accountFacade.getAccountByNumber(anyString())).willReturn(build);

        MvcResult mvcResult = mockMvc.perform(get("/api0/account/number/{number}", "1234")).andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertThat(contentAsString).contains(build.getNumber(), Double.toString(build.getBalance()), build.getCurrency().toString());
    }

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    void getAllAccount() throws Exception {
        AccountDtoResponse accountDtoResponse = AccountDtoResponse.builder().id(1L).balance(1234.3).currency(Currency.CHF).number("123").build();
        PageRequest pageRequest = PageRequest.of(1, 1);
        List<AccountDtoResponse> accountDtoResponseList = Collections.singletonList(accountDtoResponse);
        Page<AccountDtoResponse> accountPage = new PageImpl<>(accountDtoResponseList, pageRequest, accountDtoResponseList.size());

        given(accountFacade.findAll(1, 1)).willReturn(accountPage);

        mockMvc.perform(get("/api0/account"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    void deleteAccount() throws Exception {
        mockMvc.perform(delete("/api0/account")
                .param("id", String.valueOf(1L)))
                .andExpect(status().isOk());
    }

//    @WithMockUser(value = "enail1@gmail.com")
//    @Test
//    void topUpAccount() {
//
//    }
//
//    @WithMockUser(value = "enail1@gmail.com")
//    @Test
//    void withdrawFromAccount() {
//    }
//
//    @WithMockUser(value = "enail1@gmail.com")
//    @Test
//    void transfer() {
//    }
}