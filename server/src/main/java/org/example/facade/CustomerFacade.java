package org.example.facade;

import org.example.dto.request.CustomerDtoRequest;
import org.example.dto.response.CustomerDtoResponse;
import org.example.entity.Customer;
import org.example.exception.CustomerNotFound;
import org.example.service.CustomerService;
import org.example.service.imp.CustomerServiceIml;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CustomerFacade implements FacadeIml<CustomerDtoRequest, CustomerDtoResponse>, CustomerServiceIml {

    @Autowired
    CustomerService customerService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerDtoResponse save(CustomerDtoRequest obj) {
        return mapper.map(customerService.save(mapper.map(obj, Customer.class)), CustomerDtoResponse.class);
    }

    @Override
    public CustomerDtoResponse update(CustomerDtoRequest obj) {
        return mapper.map(customerService.update(mapper.map(obj, Customer.class)), CustomerDtoResponse.class);
    }

    @Override
    public void delete(CustomerDtoRequest obj) {
        customerService.delete(mapper.map(obj, Customer.class));
    }

    @Override
    public void deleteAll(List<CustomerDtoRequest> ent) {
        customerService.deleteAll(ent.stream().map(customerDto -> mapper.map(customerDto, Customer.class)).collect(Collectors.toList()));
    }

    @Override
    public void saveAll(List<CustomerDtoRequest> ent) {
        customerService.saveAll(ent.stream().map(customerDto -> mapper.map(customerDto, Customer.class)).collect(Collectors.toList()));
    }

    @Override
    public Page<CustomerDtoResponse> findAll(int page, int limit) {
        Page<CustomerDtoResponse> map = customerService.findAll(page, limit).map(customer -> mapper.map(customer, CustomerDtoResponse.class));
        return map;
    }

    @Override
    public void deleteById(Long id) {
        customerService.deleteById(id);
    }

    @Override
    public CustomerDtoResponse getById(Long id) throws CustomerNotFound {
        Customer byId = customerService.getById(id);
        CustomerDtoResponse map = mapper.map(byId, CustomerDtoResponse.class);
        return map;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return customerService.loadUserByUsername(s);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerService.existsByEmail(email);
    }
}
