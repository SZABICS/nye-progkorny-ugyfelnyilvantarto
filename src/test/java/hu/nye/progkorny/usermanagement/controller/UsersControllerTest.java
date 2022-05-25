package hu.nye.progkorny.usermanagement.controller;

import hu.nye.progkorny.usermanagement.model.entities.Users;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private UsersController controller;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertNotNull(controller);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Ügyfélnyílvántartó")));
    }

    @Test
    public void getAllFunctionGivesBackAllElementsOfListAsModel() throws Exception {
        mockMvc.perform(get("/users/alluser"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/alluser"))
                .andExpect(model().attribute("users", notNullValue()));
    }
    @Test
    public void removeFunctionWillDecreaseTheSizeOfList() throws Exception {
        mockMvc.perform(get("/users/remove/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/alluser"))
                .andExpect(model().attribute("users", hasSize(1)));
    }

    @Test
    public void shouldReturnNullToCreateFormsErrorMessages() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/create");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(null, mvcResult.getResponse().getErrorMessage());
    }
    @Test
    public void loadExistsUserFunctionWillGiveBackNowTheTestSecondUser() throws Exception {
        mockMvc.perform(get("/users/load/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/edit"))
                .andExpect(model().attribute("user", Users.getExists().get(1)));
    }

    @Test
    public void shouldReturnNullToEditFormsErrorMessages() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/edit");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(null, mvcResult.getResponse().getErrorMessage());
    }
}
