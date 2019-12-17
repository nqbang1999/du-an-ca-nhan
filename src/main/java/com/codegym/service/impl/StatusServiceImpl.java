package com.codegym.service.impl;

import com.codegym.model.Status;
import com.codegym.repository.StatusRepository;
import com.codegym.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status findById(Long id) {
        return statusRepository.findOne(id);
    }

    @Override
    public Iterable<Status> findAll() {
        return statusRepository.findAll();
    }

}
