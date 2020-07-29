package org.example.controller;

import org.example.dto.request.EmployerDtoRequest;
import org.example.dto.response.EmployerDtoResponse;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api0/employer")
public class EmployerController {



    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployerDtoResponse createEmployer(@Validated @RequestBody EmployerDtoRequest dto) {
        return null;
    }

    @PutMapping("")
    public EmployerDtoResponse updateEmployer() {
        return null;
    }
}
