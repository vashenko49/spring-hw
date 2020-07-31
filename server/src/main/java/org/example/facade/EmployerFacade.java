package org.example.facade;

import org.example.dto.request.EmployerDtoRequest;
import org.example.dto.response.EmployerDtoResponse;
import org.example.entity.Employer;
import org.example.service.EmployerService;
import org.example.service.imp.EmployerServiceIml;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployerFacade implements FacadeIml<EmployerDtoRequest, EmployerDtoResponse>, EmployerServiceIml<EmployerDtoResponse> {

    @Autowired
    EmployerService employerService;
    @Autowired
    ModelMapper mapper;

    @Override
    public EmployerDtoResponse getByName(String name) {
        return mapper.map(employerService.getByName(name), EmployerDtoResponse.class);
    }

    @Override
    public void addCustomerToEmployer(Long customerId, Long employerId) {
        employerService.addCustomerToEmployer(customerId, employerId);
    }

    @Override
    public void removeCustomerFromEmployer(Long customerId, Long employerId) {
        employerService.removeCustomerFromEmployer(customerId, employerId);
    }

    @Override
    public EmployerDtoResponse save(EmployerDtoRequest obj) {
        return mapper.map(employerService.save(mapper.map(obj, Employer.class)), EmployerDtoResponse.class);
    }

    @Override
    public EmployerDtoResponse update(EmployerDtoRequest obj) {
        return mapper.map(employerService.update(mapper.map(obj, Employer.class)), EmployerDtoResponse.class);
    }

    @Override
    public void delete(EmployerDtoRequest obj) {
        employerService.delete(mapper.map(obj, Employer.class));
    }

    @Override
    public void deleteAll(List<EmployerDtoRequest> ent) {
        employerService.deleteAll(ent.stream().map(employerDto -> mapper.map(employerDto, Employer.class)).collect(Collectors.toList()));
    }

    @Override
    public void saveAll(List<EmployerDtoRequest> ent) {
        employerService.saveAll(ent.stream().map(employerDto -> mapper.map(employerDto, Employer.class)).collect(Collectors.toList()));
    }

    @Override
    public Page<EmployerDtoResponse> findAll(int page, int limit) {
        Page<EmployerDtoResponse> map = employerService.findAll(page, limit).map(employer -> mapper.map(employer, EmployerDtoResponse.class));
        return map;
    }

    @Override
    public void deleteById(Long id) {
        employerService.deleteById(id);
    }

    @Override
    public EmployerDtoResponse getById(Long id) {
        return mapper.map(employerService.getById(id), EmployerDtoResponse.class);
    }
}
