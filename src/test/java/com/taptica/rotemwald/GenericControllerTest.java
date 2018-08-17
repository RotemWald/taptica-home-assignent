package com.taptica.rotemwald;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(GenericController.class)
public class GenericControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloRouteTest() throws Exception {
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    public void fiboRouteTest() throws Exception {
        this.mockMvc.perform(get("/fibo?n=0")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
        this.mockMvc.perform(get("/fibo?n=3")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("2")));
        this.mockMvc.perform(get("/fibo?n=6")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("8")));
        this.mockMvc.perform(get("/fibo")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
        this.mockMvc.perform(get("/fibo?n=bla")).andDo(print()).andExpect(status().isBadRequest());
    }
}