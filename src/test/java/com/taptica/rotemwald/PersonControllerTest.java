package com.taptica.rotemwald;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taptica.rotemwald.controllers.PersonController;
import com.taptica.rotemwald.entities.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Person route test (save and get expected user)
     * @throws Exception
     */
    @Test
    public void saveAndGetTest() throws Exception {
        Person expectedPerson = new Person("TestFirstName", "TestLastName");

        // Send request to save person created above
        MvcResult saveResult =
                this.mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedPerson)))
                .andExpect(status().isOk())
                .andReturn();
        // Get the id of person created (in database), update the id in the person object created above
        String personId = saveResult.getResponse().getContentAsString();
        expectedPerson.setId(Integer.parseInt(personId));

        // Send request to get person with id given above
        MvcResult getResult =
                this.mockMvc.perform(get("/person/" + personId))
                .andExpect(status().isOk())
                .andReturn();
        // Create person requested from string given in response
        Person actualPerson = objectMapper.readValue(getResult.getResponse().getContentAsString(), Person.class);

        // Check that created person object and test-given person object are equal
        Assert.assertEquals(expectedPerson, actualPerson);
    }
}