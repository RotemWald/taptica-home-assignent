package com.taptica.rotemwald;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taptica.rotemwald.controllers.GenericController;
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

    /**
     * Hello World route test
     * @throws Exception
     */
    @Test
    public void helloRouteTest() throws Exception {
        this.mockMvc.perform(get("/hello")) // after request, response should be "Hello World"
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    /**
     * Fibonacci route test
     * @throws Exception
     */
    @Test
    public void fiboRouteTest() throws Exception {
        /* General test cases */
        this.mockMvc.perform(get("/fibo?n=0")) // element #0 is 0
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));

        this.mockMvc.perform(get("/fibo?n=3")) // element #3 is 2
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2")));

        this.mockMvc.perform(get("/fibo?n=6")) // element #6 is 8
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("8")));

        /* Special test cases */
        this.mockMvc.perform(get("/fibo")) // request without parameter, expected parameter to be zero
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));

        this.mockMvc.perform(get("/fibo?n=bla")) // request with string parameter, expected error
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}