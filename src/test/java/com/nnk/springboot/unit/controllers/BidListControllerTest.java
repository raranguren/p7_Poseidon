package com.nnk.springboot.unit.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@RunWith(SpringRunner.class)
public class BidListControllerTest {

    @Autowired
    MockMvc mockMvc;

}
