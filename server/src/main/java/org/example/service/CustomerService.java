package org.example.service;

import org.example.entity.Customer;
import org.example.repos.CustomerRepository;
import org.example.service.imp.ServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("customerService")
@Transactional
public class CustomerService implements ServiceIml<Customer> {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer obj) {
        return customerRepository.save(obj);
    }

    @Override
    public void delete(Customer obj) {
        customerRepository.delete(obj);
    }

    @Override
    public void deleteAll(List<Customer> ent) {
        customerRepository.deleteAll(ent);
    }

    @Override
    public void saveAll(List<Customer> ent) {
        customerRepository.saveAll(ent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAll(int page, int limit) {
        return customerRepository.findAll(PageRequest.of(page, limit));
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getById(Long id) {
        return customerRepository.findById(id).get();
    }
}