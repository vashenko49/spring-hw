package org.example.service.imp;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ServiceIml<T> {
    T save(T obj);

    void delete(T obj);

    void deleteAll(List<T> ent);

    void saveAll(List<T> ent);

    Page<T> findAll(int page, int limit);

    void deleteById(Long id);

    T getById(Long id);
}
