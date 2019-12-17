package com.codegym.controllers;


import com.codegym.model.Status;
import com.codegym.model.Task;
import com.codegym.service.StatusService;
import com.codegym.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class TaskController {

    @ModelAttribute("status")
    public Iterable<Status> statuses(){
        return statusService.findAll();
    }

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

    @GetMapping(value = "/create-task")
    public ModelAndView showCreateTaskForm() {
        ModelAndView modelAndView = new ModelAndView("task/create");
        modelAndView.addObject("task", new Task());
        return modelAndView;
    }

    @PostMapping(value = "/create-task")
    public String createTask(@Validated @ModelAttribute Task task,
                                BindingResult bindingResult,
                                RedirectAttributes redirect) {
        if(bindingResult.hasFieldErrors()) {
            return "task/create";
        }
        taskService.save(task);
        redirect.addFlashAttribute("message", "create success");
        return "redirect:/create-task";
    }

    @GetMapping(value = "/edit-task/{id}")
    public ModelAndView showEditTaskForm(@PathVariable Long id) {
        Task task = taskService.findById(id);
        ModelAndView modelAndView = new ModelAndView("task/edit");
        modelAndView.addObject("task", task);
        return modelAndView;
    }

    @PostMapping(value = "/edit-task")
    public ModelAndView editTask(@Validated @ModelAttribute Task task) {
        taskService.save(task);
        ModelAndView modelAndView = new ModelAndView("task/edit");
        modelAndView.addObject("task", task);
        modelAndView.addObject("message", "task updated");
        return modelAndView;
    }



}
