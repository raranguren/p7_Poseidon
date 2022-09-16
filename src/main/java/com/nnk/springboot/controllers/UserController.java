package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private static final Logger log = LogManager.getLogger("UserController");

    // Inject User service
    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        List<User> list = service.readAll();
        model.addAttribute("users", list);
        log.info("GET /user/list - Showing a list of {} users", list.size());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser() {
        log.info("GET /user/add - Showing form");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            service.create(user);
            log.info("POST /user/validate - ADDED 1 new entry, returning to list");
            model.addAttribute("users", service.readAll());
            return "redirect:/user/list";
        }
        log.info("POST /user/validate - HAS ERRORS, showing form");
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = service.read(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        log.info("GET /user/update({}) - EXISTS - Showing form with empty password", id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("POST /user/update({}) - HAS ERRORS, showing form", id);
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        service.update(user);
        log.info("POST /user/update({}) - UPDATED 1 entry, returning to list", id);
        model.addAttribute("users", service.readAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = service.read(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        service.delete(user.getId());
        log.info("GET /user/delete({}) - DELETED 1 entry, returning to list", id);
        model.addAttribute("users", service.readAll());
        return "redirect:/user/list";
    }
}
