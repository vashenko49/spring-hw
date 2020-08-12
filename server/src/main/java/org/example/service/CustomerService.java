package org.example.service;

import org.example.entity.Customer;
import org.example.repos.CustomerRepository;
import org.example.service.imp.CustomerServiceIml;
import org.example.service.imp.ServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("customerService")
@Transactional(isolation = Isolation.SERIALIZABLE)
public class CustomerService implements ServiceIml<Customer>, CustomerServiceIml {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer obj) {
        return customerRepository.save(obj);
    }

    @Override
    public Customer update(Customer obj) {
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
        Page<Customer> all = customerRepository.findAll(PageRequest.of(page, limit));
        return all;
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getById(Long id) {
        Customer customer = customerRepository.findById(id).get();
        return customer;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Customer> user = customerRepository.findByEmail(s);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found userName"));
        return user.get();
    }
}
