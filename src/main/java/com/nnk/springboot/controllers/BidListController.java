package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {

    private static final Logger log = LogManager.getLogger("BidListController");

    // Inject Bid service
    private final BidListService service;
    public BidListController(BidListService service) {
        this.service = service;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // call service find all bids to show to the view
        List<BidList> list = service.readAll();
        model.addAttribute("list", list);
        log.info("GET /bidList/list - Showing a list of {} bids", list.size());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bidList) {
        log.info("GET /bidlist/add - Showing form");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {
        // check data valid and save to db, after saving return bid list
        if (result.hasErrors()) {
            log.info("POST /bidList/validate - HAS ERRORS, showing form");
            return "bidList/add";
        }
        service.create(bidList);
        log.info("POST /bidList/validate - ADDED 1 new entry, returning to list");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // get Bid by Id and to model then show to the form
        Optional<BidList> existingBid = service.read(id);
        if (existingBid.isPresent()) {
            model.addAttribute("bidList", existingBid.get());
            log.info("GET /bidList/update({}) - EXISTS - Showing form", id);
            return "bidList/update";
        }
        log.info("GET /bidList/update({}) - DOES NOT EXIST - returning to list", id);
        return "redirect:/bidList/list";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            log.info("POST /bidList/update({}) - HAS ERRORS, showing form", id);
            return "bidList/update/" + id;
        }
        bidList.setBidListId(id);
        service.update(bidList);
        log.info("POST /bidList/update({}) - UPDATED 1 entry, returning to list", id);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // Find Bid by Id and delete the bid, return to Bid list
        service.delete(id);
        log.info("GET /bidList/delete({}) - DELETED 1 entry, returning to list", id);
        return "redirect:/bidList/list";
    }
}
