package org.example.repos;

import org.example.entity.Employer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployerRepository extends PagingAndSortingRepository<Employer, Long> {
    Employer getByName(String name);
}
