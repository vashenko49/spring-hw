package org.example.controller;

import org.example.dto.request.EmployerDtoRequest;
import org.example.dto.request.groups.New;
import org.example.dto.request.groups.Update;
import org.example.dto.response.EmployerDtoResponse;
import org.example.facade.EmployerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api0/employer")
public class EmployerController {
    @Autowired
    EmployerFacade employerFacade;

    @GetMapping("/{id}")
    public EmployerDtoResponse getEmployerById(@PathVariable(value = "id") Long id) {
        return employerFacade.getById(id);
    }

    @GetMapping("")
    public Page<EmployerDtoResponse> getAllEmployer(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return employerFacade.findAll(page, limit);
    }

    @PutMapping("/add")
    public void addCustomerToEmployer(
            @RequestBody HashMap<String, Long> data) {
        employerFacade.addCustomerToEmployer(data.get("customerId"), data.get("employerId"));
    }

    @PutMapping("/remove")
    public void removeCustomerFromEmployer(
            @RequestBody HashMap<String, Long> data
    ) {
        employerFacade.removeCustomerFromEmployer(data.get("customerId"), data.get("employerId"));
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployerDtoResponse createEmployer(
            @Validated(New.class) @RequestBody EmployerDtoRequest employer
    ) {
        return employerFacade.save(employer);
    }

    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployerDtoResponse updateEmployer(@Validated(Update.class) @RequestBody EmployerDtoRequest employer) {
        return employerFacade.update(employer);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployer(@PathVariable(value = "id") Long employerId) {
        employerFacade.deleteById(employerId);
    }
}
