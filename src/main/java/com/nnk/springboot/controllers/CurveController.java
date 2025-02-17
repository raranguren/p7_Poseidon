package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CurveController {

    private static final Logger log = LogManager.getLogger("CurveController");

    // Inject Curve Point service
    private final CurvePointService service;
    public CurveController(CurvePointService service) {
        this.service = service;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model, Principal principal)
    {
        // find all Curve Point, add to model
        List<CurvePoint> list = service.readAll();
        model.addAttribute("list", list);
        String username = principal instanceof OAuth2AuthenticationToken ?
                ((OAuth2AuthenticationToken)principal).getPrincipal().getAttributes().get("login").toString() :
                principal.getName();
        model.addAttribute("list", list);
        model.addAttribute("username", username);
        log.info("GET /curvePoint/list - Showing a list of {} curve points", list.size());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addForm(CurvePoint curvePoint) {
        log.info("GET /curvePoint/add - Showing form");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Curve list
        if (result.hasErrors()) {
            log.info("POST /curvePoint/validate - HAS ERRORS, showing form");
            return "curvePoint/add";
        }
        service.create(curvePoint);
        log.info("POST /curvePoint/validate - ADDED 1 new entry, returning to list");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get CurvePoint by Id and to model then show to the form
        Optional<CurvePoint> existingObject = service.read(id);
        if (existingObject.isPresent()) {
            model.addAttribute("curvePoint", existingObject.get());
            log.info("GET /curvePoint/update({}) - EXISTS - Showing form", id);
            return "curvePoint/update";
        }
        log.info("GET /curvePoint/update({}] - DOES NOT EXIST - returning to list", id);
        return "redirect:/curvePoint/list";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // check required fields, if valid call service to update Curve and return Curve list
        if (result.hasErrors()) {
            log.info("POST /curvePoint/UPDATE({}) - HAS ERRORS, showing form", id);
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        service.update(curvePoint);
        log.info("POST /curvePoint/UPDATE({}) - updated 1 ENTRY, RETURNING TO LIST", id);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        // Find Curve by Id and delete the Curve, return to Curve list
        service.delete(id);
        log.info("GET /curvePoint/delete({}) - DELETED 1 entry, returning to list", id);
        return "redirect:/curvePoint/list";
    }
}
