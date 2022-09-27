package com.nnk.springboot.unit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TradeController.class, secure = false)
@RunWith(SpringRunner.class)
public class TradeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TradeService service;

    Trade exampleObject;

    @Before
    public void before_each_test() {
        exampleObject = new Trade("account", "type");
    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/trade/list"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("trade/list"))
                .andExpect(model().attributeExists("list"));
    }

    @Test
    public void add_form() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andExpect((status().is2xxSuccessful()))
                .andExpect(view().name("trade/add"));
    }

    @Test
    public void update_form() throws Exception {
        when(service.read(1234)).thenReturn(Optional.of(exampleObject));
        mockMvc.perform(get("/trade/update/1234"))
                .andExpect((status().is2xxSuccessful()))
                .andExpect(view().name("trade/update"));
    }

    @Test
    public void add_action() throws Exception {
        String json = new ObjectMapper().writeValueAsString(exampleObject);
        mockMvc.perform(post("/trade/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void update_action() throws Exception {
        exampleObject.setTradeId(1234);
        when(service.read(1234)).thenReturn(Optional.of(exampleObject));
        String json = new ObjectMapper().writeValueAsString(exampleObject);
        mockMvc.perform(post("/trade/update/1234")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void delete_action() throws Exception {
        when(service.read(1234)).thenReturn(Optional.of(exampleObject));
        mockMvc.perform(get("/trade/delete/1234"))
                .andExpect(status().is3xxRedirection());
    }

}
