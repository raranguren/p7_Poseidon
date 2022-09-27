package com.nnk.springboot.unit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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

@WebMvcTest(controllers = CurveController.class, secure = false)
@RunWith(SpringRunner.class)
public class CurvePointControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CurvePointService service;

    CurvePoint exampleObject;

    @Before
    public void before_each_test() {
        exampleObject = new CurvePoint(52, 11.11, 20.20);
    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("list"));
    }

    @Test
    public void add_form() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect((status().is2xxSuccessful()))
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    public void update_form() throws Exception {
        when(service.read(1234)).thenReturn(Optional.of(exampleObject));
        mockMvc.perform(get("/curvePoint/update/1234"))
                .andExpect((status().is2xxSuccessful()))
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    public void add_action() throws Exception {
        String json = new ObjectMapper().writeValueAsString(exampleObject);
        mockMvc.perform(post("/curvePoint/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void update_action() throws Exception {
        exampleObject.setId(1234);
        when(service.read(1234)).thenReturn(Optional.of(exampleObject));
        String json = new ObjectMapper().writeValueAsString(exampleObject);
        mockMvc.perform(post("/curvePoint/update/1234")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void delete_action() throws Exception {
        when(service.read(1234)).thenReturn(Optional.of(exampleObject));
        mockMvc.perform(get("/curvePoint/delete/1234"))
                .andExpect(status().is3xxRedirection());
    }

}
