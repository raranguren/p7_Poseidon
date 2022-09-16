package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class RatingController {
    private static final Logger log = LogManager.getLogger("RatingController");

    // Inject Rating service
    private final RatingService service;
    public RatingController(RatingService service) {
        this.service = service;
    }

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // find all Rating, add to model
        List<Rating> list = service.readAll();
        model.addAttribute("list", list);
        log.info("GET /rating/list - Showing a list of {} ratings", list.size());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        log.info("GET /rating/add - Showing form");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Rating list
        if (result.hasErrors()) {
            log.info("POST /rating/validate - HAS ERRORS, showing form");
            return "rating/add";
        }
        service.create(rating);
        log.info("POST /rating/validate - ADDED 1 new entry, returning to list");
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get Rating by Id and to model then show to the form
        Optional<Rating> existingObject = service.read(id);
        if (existingObject.isPresent()) {
            model.addAttribute("rating", existingObject.get());
            log.info("GET /rating/update({}) - EXISTS - Showing form", id);
            return "rating/update";
        }
        log.info("GET /rating/update({}) - DOES NOT EXIST - returning to list", id);
        return "redirect:/rating/list";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // check required fields, if valid call service to update Rating and return Rating list
        if (result.hasErrors()) {
            log.info("POST /rating/update({}) - HAS ERRORS, showing form", id);
            return "rating/update/" + id;
        }
        rating.setId(id);
        service.update(rating);
        log.info("POST /rating/update({}) - UPDATED 1 entry, returning to list", id);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // Find Rating by Id and delete the Rating, return to Rating list
        service.delete(id);
        log.info("GET /rating/delete({}) - DELETED 1 entry, returning to list", id);
        return "redirect:/rating/list";
    }
}
