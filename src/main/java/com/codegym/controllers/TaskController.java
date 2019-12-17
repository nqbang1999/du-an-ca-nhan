package com.codegym.controllers;


import com.codegym.model.Task;
import com.codegym.service.StatusService;
import com.codegym.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private StatusService statusService;

    @GetMapping(value = "/home")
    public ModelAndView listTask(@RequestParam Optional<String> search,
                                 @PageableDefault(size = 5, sort = "id") Pageable pageable) {
        Page<Task> tasks;
        if (search.isPresent()) {
            tasks = taskService.findAllByTitle(search.get(), pageable);
        } else {
            tasks = taskService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("task/home");
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }
}
