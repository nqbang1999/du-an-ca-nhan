package com.codegym.service;

import com.codegym.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
Page<Task> findAll(Pageable pageable);
Page<Task> findAllByTitle(String title, Pageable pageable);
}
