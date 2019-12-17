package com.codegym.service;

import com.codegym.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StatusService {

    Status findById(Long id);

    Iterable<Status> findAll();
}
