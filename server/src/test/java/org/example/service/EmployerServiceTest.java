package org.example.service;

import org.example.entity.Employer;
import org.example.repos.EmployerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class EmployerServiceTest {

    @MockBean
    private EmployerRepository employerRepository;

    @Autowired
    private EmployerService employerService;

    @Test
    void save() {
        Employer employer = Employer.builder().address("test").customers(null).name("test").build();
        given(employerRepository.save(any())).willReturn(employer);

        Employer save = employerService.save(employer);

        assertThat(save).isEqualTo(employer);
    }

    @Test
    void update() {
        Employer employer = Employer.builder().address("test").customers(null).name("test").build();
        given(employerRepository.save(any())).willReturn(employer);
        given(employerRepository.findById(any())).willReturn(Optional.ofNullable(employer));

        Employer save = employerService.update(employer);

        assertThat(save).isEqualTo(employer);
    }
}