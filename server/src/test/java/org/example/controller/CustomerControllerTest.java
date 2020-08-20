package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.response.CustomerDtoResponse;
import org.example.facade.CustomerFacade;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerFacade customerFacade;

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    void getCustomerById() throws Exception {
        CustomerDtoResponse customerDtoResponse = CustomerDtoResponse.builder()
                .accounts(null)
                .employers(null)
                .age(1)
                .email("test")
                .name("test")
                .phone("test")
                .id(1L)
                .build();

        given(customerFacade.getById(anyLong())).willReturn(customerDtoResponse);

        MvcResult mvcResult = mockMvc.perform(get("/api0/customer/{id}", 1L)).andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertThat(contentAsString).contains(customerDtoResponse.getEmail(), customerDtoResponse.getPhone(), customerDtoResponse.getName());
    }

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    void getAllCustomer() throws Exception {
        CustomerDtoResponse customerDtoResponse = CustomerDtoResponse.builder()
                .accounts(null)
                .employers(null)
                .age(1)
                .email("test")
                .name("test")
                .phone("test")
                .id(1L)
                .build();

        PageRequest pageRequest = PageRequest.of(1, 1);
        List<CustomerDtoResponse> customerDtoResponses = Collections.singletonList(customerDtoResponse);
        PageImpl<CustomerDtoResponse> customerDtoResponsesPage = new PageImpl<>(customerDtoResponses, pageRequest, customerDtoResponses.size());


        given(customerFacade.findAll(1, 1)).willReturn(customerDtoResponsesPage);

        mockMvc.perform(get("/api0/customer"))
                .andExpect(status().isOk());
    }

//    @WithMockUser(value = "enail1@gmail.com")
//    @Test
//    void createNewCustomerTest() throws Exception {
//        CustomerDtoResponse customerDtoResponse = CustomerDtoResponse.builder()
//                .accounts(null)
//                .employers(null)
//                .age(1)
//                .email("test45@gmail.com")
//                .name("test")
//                .phone("+380965122529")
//                .id(1L)
//                .build();
//
//        CustomerDtoRequest customerDtoRequest = CustomerDtoRequest.builder()
//                .employers(null)
//                .age(1)
//                .email("test45@gmail.com")
//                .name("test")
//                .phone("+380965122529")
//                .build();
//
//        given(customerFacade.save(any())).willReturn(customerDtoResponse);
//
//        MvcResult mvcResult = mockMvc.perform(post("/api0/customer")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(customerDtoRequest))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//
//        assertThat(contentAsString).contains(customerDtoResponse.getId().toString(), customerDtoResponse.getName());
//    }
}