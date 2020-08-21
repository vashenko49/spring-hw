package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.CustomerIdAndEmployerIdRequest;
import org.example.dto.request.EmployerDtoRequest;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;
import org.example.dto.response.EmployerDtoResponse;
import org.example.facade.EmployerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api0/employer")
public class EmployerController {
    @Autowired
    EmployerFacade employerFacade;

    @GetMapping("{id}")
    public EmployerDtoResponse getEmployerById(@PathVariable(value = "id") Long id) {
        log.info("Find employer by {} id", id.toString());
        return employerFacade.getById(id);
    }

    @GetMapping("")
    public Page<EmployerDtoResponse> getAllEmployer(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        log.info("Find all employers");
        return employerFacade.findAll(page, limit);
    }

    @PutMapping("add")
    public void addCustomerToEmployer(
            @Validated @RequestBody CustomerIdAndEmployerIdRequest data
    ) {
        log.info("Add customer to employer");
        employerFacade.addCustomerToEmployer(data.getCustomerId(), data.getEmployerId());
    }

    @PutMapping("remove")
    public void removeCustomerFromEmployer(
            @Validated @RequestBody CustomerIdAndEmployerIdRequest data
    ) {
        log.info("remove customer to employer");
        employerFacade.removeCustomerFromEmployer(data.getCustomerId(), data.getEmployerId());
    }

    @PostMapping(path = "")
    public EmployerDtoResponse createEmployer(
            @Validated(New.class) @RequestBody EmployerDtoRequest employer
    ) {
        log.info("create employer");
        return employerFacade.save(employer);
    }

    @PutMapping(path = "")
    public EmployerDtoResponse updateEmployer(@Validated(Update.class) @RequestBody EmployerDtoRequest employer) {
        log.info("update employer");
        return employerFacade.update(employer);
    }

    @DeleteMapping("")
    public void deleteEmployer(@RequestParam(value = "id") Long employerId) {
        log.info("delete employer");
        employerFacade.deleteById(employerId);
    }
}
