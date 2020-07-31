package org.example.service;

import org.example.entity.Customer;
import org.example.entity.Employer;
import org.example.repos.CustomerRepository;
import org.example.repos.EmployerRepository;
import org.example.service.imp.EmployerServiceIml;
import org.example.service.imp.ServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employerService")
@Transactional
public class EmployerService implements ServiceIml<Employer>, EmployerServiceIml<Employer> {

    @Autowired
    EmployerRepository employerRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Employer save(Employer obj) {
        return employerRepository.save(obj);
    }

    @Override
    public Employer update(Employer obj) {
        Employer employer = employerRepository.findById(obj.getId()).get();
        employer.setAddress(obj.getAddress());
        employer.setName(obj.getName());
        return employerRepository.save(employer);
    }

    @Override
    public void delete(Employer obj) {
        employerRepository.delete(obj);
    }

    @Override
    public void deleteAll(List<Employer> ent) {
        employerRepository.deleteAll(ent);
    }

    @Override
    public void saveAll(List<Employer> ent) {
        employerRepository.saveAll(ent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employer> findAll(int page, int limit) {
        return employerRepository.findAll(PageRequest.of(page, limit));
    }

    @Override
    public void deleteById(Long id) {
        Employer employer = employerRepository.findById(id).get();
        for (Customer customer : employer.getCustomers()) {
            customer.getEmployers().remove(employer);
        }
        employer.getCustomers().clear();
        employerRepository.save(employer);
        employerRepository.delete(employer);
    }

    @Override
    @Transactional(readOnly = true)
    public Employer getById(Long id) {
        return employerRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Employer getByName(String name) {
        return employerRepository.getByName(name);
    }

    @Override
    public void addCustomerToEmployer(Long customerId, Long employerId) {
        Customer customer = customerRepository.findById(customerId).get();
        Employer employer = employerRepository.findById(employerId).get();

        employer.getCustomers().add(customer);
        customer.getEmployers().add(employer);

        employerRepository.save(employer);
    }

    @Override
    public void removeCustomerFromEmployer(Long customerId, Long employerId) {
        Customer customer = customerRepository.findById(customerId).get();
        Employer employer = employerRepository.findById(employerId).get();

        employer.getCustomers().remove(customer);
        customer.getEmployers().remove(employer);

        employerRepository.save(employer);
    }
}
