package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.request.CustomerIdAndEmployerIdRequest;
import org.example.dto.request.EmployerDtoRequest;
import org.example.dto.response.EmployerDtoResponse;
import org.example.facade.EmployerFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    EmployerFacade employerFacade;

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    public void testGetEmployerById() throws Exception {
        EmployerDtoResponse employer = new EmployerDtoResponse(1L, "mock", "mock");
        given(employerFacade.getById(anyLong())).willReturn(employer);
        MvcResult mvcResult = mockMvc.perform(get("/api0/employer/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertThat(contentAsString).contains(employer.getName(), employer.getAddress());
    }

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    public void testGetAllEmployer() throws Exception {
        EmployerDtoResponse employer = new EmployerDtoResponse(1L, "mock", "mock");
        PageRequest pageRequest = PageRequest.of(1, 1);
        List<EmployerDtoResponse> employerList = Collections.singletonList(employer);
        Page<EmployerDtoResponse> employerPage = new PageImpl<>(employerList, pageRequest, employerList.size());

        given(employerFacade.findAll(1, 1)).willReturn(employerPage);

        mockMvc.perform(get("/api0/employer"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    public void testAddCustomerToEmployer() throws Exception {
        CustomerIdAndEmployerIdRequest customerIdAndEmployerIdRequest = CustomerIdAndEmployerIdRequest.builder()
                .customerId(1L)
                .employerId(2L)
                .build();


        mockMvc.perform(put("/api0/employer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerIdAndEmployerIdRequest)))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    public void testAddCustomerToEmployerNoContent() throws Exception {
        mockMvc.perform(put("/api0/employer/add")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testAddCustomerToEmployerUnAuth() throws Exception {
        mockMvc.perform(put("/api0/employer/add")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    public void testRemoveCustomerFromEmployer() throws Exception {
        CustomerIdAndEmployerIdRequest customerIdAndEmployerIdRequest = CustomerIdAndEmployerIdRequest.builder()
                .customerId(1L)
                .employerId(2L)
                .build();


        mockMvc.perform(put("/api0/employer/remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerIdAndEmployerIdRequest)))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    public void testCreateEmployer() throws Exception {
        EmployerDtoRequest request = EmployerDtoRequest.builder().address("1234").id(1l).name("1234").build();
        EmployerDtoResponse response = EmployerDtoResponse.builder().address("1234").id(1l).name("1234").build();

        given(employerFacade.save(any())).willReturn(response);

        mockMvc.perform(post("/api0/employer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "enail1@gmail.com")
    @Test
    public void testDeleteEmployer() throws Exception {
        mockMvc.perform(delete("/api0/employer")
                .param("id", String.valueOf(1L)))
                .andExpect(status().isOk());
    }
}