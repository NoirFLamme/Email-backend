package com.example.email;

import com.example.email.objects.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Test
    public void testgetAllTodos() throws Exception
    {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void whengetAllTodos_thenReturnJsonArray() throws Exception
    {
        Account account1 = new Account("1", "ramy", "123", "ramy@mail.com", null, null);
        Account account2 = new Account("2", "zyad", "321", "zyad@mail.com", null, null);
        List<Account> data = Arrays.asList(account1, account2);

        given(emailService.findAll()).willReturn(data);

        mockMvc.perform(
                get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo(account1.getId())));
    }

    @Test
    public void whenPostAccount_ThenCreateAccount() throws Exception
    {
        Account account1 = new Account("1", "ramy", "123", "ramy@mail.com", null, null);

        given(emailService.create(Mockito.any(Account.class))).willReturn(account1);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(
                post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(account1))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", equalTo(account1.getEmail())));
    }
}
