package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {
    private static final Logger log = LogManager.getLogger("TradeController");

    // Inject Trade service
    private final TradeService service;
    public TradeController(TradeService service) {
        this.service = service;
    }

    @RequestMapping("/trade/list")
    public String home(Model model, Principal principal)
    {
        // find all Trade, add to model
        List<Trade> list = service.readAll();
        String username = principal instanceof OAuth2AuthenticationToken ?
                ((OAuth2AuthenticationToken)principal).getPrincipal().getAttributes().get("login").toString() :
                principal.getName();
        model.addAttribute("list", list);
        model.addAttribute("username", username);        log.info("GET /trade/list - Showing a list of {} trades", list.size());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
        log.info("GET /trade/add - showing form");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Trade list
        if (result.hasErrors()) {
            log.info("POST /trade/validate - HAS ERRORS, showing form");
            return "trade/add";
        }
        service.create(trade);
        log.info("POST /trade/validate - ADDED 1 new entry, returning to list");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get Trade by Id and to model then show to the form
        Optional<Trade> existingTrade = service.read(id);
        if (existingTrade.isPresent()) {
            model.addAttribute("trade", existingTrade.get());
            log.info("GET /trade/update({}) - EXISTS - showing form", id);
            return "trade/update";
        }
        log.info("GET /trade/update({}) - DOES NOT EXIST - returning to list", id);
        return "redirect:/trade/list";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // check required fields, if valid call service to update Trade and return Trade list
        if (result.hasErrors()) {
            log.info("POST /trade/update({}) - HAS ERRORS, showing form", id);
            return "trade/update";
        }
        trade.setTradeId(id);
        service.update(trade);
        log.info("POST /trade/update({}) - UPDATED 1 entry, returning to list", id);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // Find Trade by Id and delete the Trade, return to Trade list
        service.delete(id);
        log.info("GET /rating/delete({}) - DELETED 1 entry, returning to list", id);
        return "redirect:/trade/list";
    }
}
