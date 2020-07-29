package org.example.facade;

import org.example.dto.response.CustomerDtoResponse;
import org.example.entity.Customer;
import org.example.service.CustomerService;
import org.example.service.imp.ServiceIml;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CustomerFacade implements ServiceIml<CustomerDtoResponse> {

    @Autowired
    CustomerService customerService;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerDtoResponse save(CustomerDtoResponse obj) {
        return mapper.map(customerService.save(mapper.map(obj, Customer.class)), CustomerDtoResponse.class);
    }

    @Override
    public void delete(CustomerDtoResponse obj) {
        customerService.delete(mapper.map(obj, Customer.class));
    }

    @Override
    public void deleteAll(List<CustomerDtoResponse> ent) {
        customerService.deleteAll(ent.stream().map(customerDto -> mapper.map(customerDto, Customer.class)).collect(Collectors.toList()));
    }

    @Override
    public void saveAll(List<CustomerDtoResponse> ent) {
        customerService.saveAll(ent.stream().map(customerDto -> mapper.map(customerDto, Customer.class)).collect(Collectors.toList()));
    }

    @Override
    public Page<CustomerDtoResponse> findAll(int page, int limit) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        customerService.deleteById(id);
    }

    @Override
    public CustomerDtoResponse getById(Long id) {
        return mapper.map(customerService.getById(id), CustomerDtoResponse.class);
    }
}
