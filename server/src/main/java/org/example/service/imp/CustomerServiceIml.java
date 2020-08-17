package org.example.service.imp;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerServiceIml extends UserDetailsService {
    boolean existsByEmail(String email);
}
