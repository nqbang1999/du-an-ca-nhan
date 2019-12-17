package com.codegym.service.impl;

import com.codegym.model.Task;
import com.codegym.repository.TaskRepository;
import com.codegym.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class TaskServiceImpl implements TaskService {
@Autowired
private TaskRepository taskRepository;

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Page<Task> findAllByTitle(String title, Pageable pageable) {
        return taskRepository.findAllByTitleContaining(title, pageable);
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        taskRepository.delete(id);
    }
}
