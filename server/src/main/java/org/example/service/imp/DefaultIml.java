package org.example.service.imp;

import org.example.exception.CustomerNotFound;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DefaultIml<EntReq, EntRes> {
    EntRes save(EntReq obj) throws CustomerNotFound;
    EntRes update(EntReq obj);

    void delete(EntReq obj);

    void deleteAll(List<EntReq> ent);

    void saveAll(List<EntReq> ent);

    Page<EntRes> findAll(int page, int limit);

    void deleteById(Long id);

    EntRes getById(Long id) throws CustomerNotFound;
}
