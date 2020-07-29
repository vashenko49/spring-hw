package org.example.service;

import org.example.entity.Employer;
import org.example.repos.EmployerRepository;
import org.example.service.imp.EmployerServiceIml;
import org.example.service.imp.ServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employerService")
@Transactional
public class EmployerService implements ServiceIml<Employer>, EmployerServiceIml<Employer> {

    @Autowired
    EmployerRepository employerRepository;

    @Override
    public Employer save(Employer obj) {
        return employerRepository.save(obj);
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
        employerRepository.deleteById(id);
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
}
