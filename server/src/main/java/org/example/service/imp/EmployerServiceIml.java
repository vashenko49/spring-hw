package org.example.service.imp;


public interface EmployerServiceIml<T> {
    T getByName(String name);
    void addCustomerToEmployer( Long customerId,Long employerId);
    void removeCustomerFromEmployer( Long customerId,Long employerId);
}
