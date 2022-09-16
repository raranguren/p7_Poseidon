package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleNameController {
    private static final Logger log = LogManager.getLogger("RuleNameController");

    //Inject RuleName service
    private final RuleNameService service;
    public RuleNameController(RuleNameService service) {
        this.service = service;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // find all RuleName, add to model
        List<RuleName> list = service.readAll();
        model.addAttribute("list", list);
        log.info("GET /ruleName/list - Showing a list of {} trades", list.size());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        log.info("GET /ruleName/add - Showing form");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // check data valid and save to db, after saving return RuleName list
        if (result.hasErrors()) {
            log.info("POST /ruleName/validate - HAS ERRORS, showing form");
            return "ruleName/add";
        }
        service.create(ruleName);
        log.info("POST /ruleName/validate - ADDED 1 new rule name, returning to list");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get RuleName by Id and to model then show to the form
        Optional<RuleName> existingObject = service.read(id);
        if (existingObject.isPresent()) {
            model.addAttribute("ruleName", existingObject.get());
            log.info("GET /ruleName/update({}) - EXISTS - Showing form", id);
            return "ruleName/update";
        }
        log.info("GET /ruleName/update({}) - DOES NOT EXIST - returning to list", id);
        return "redirect:/ruleName/list";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // check required fields, if valid call service to update RuleName and return RuleName list
        if (result.hasErrors()) {
            log.info("POST /ruleName/update({}) - HAS ERRORS, showing form", id);
            return "ruleName/update/" + id;
        }
        ruleName.setId(id);
        service.update(ruleName);
        log.info("POST /ruleName/update({}) - UPDATED 1 rule name, returning to list", id);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // Find RuleName by Id and delete the RuleName, return to Rule list
        service.delete(id);
        log.info("get /ruleName/delete({}) - DELETED 1 entry, returning to list", id);
        return "redirect:/ruleName/list";
    }
}
