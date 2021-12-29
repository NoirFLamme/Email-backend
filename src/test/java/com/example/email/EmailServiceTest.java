package com.example.email;
import com.example.email.objects.Account;
import com.example.email.objects.Contact;
import com.example.email.objects.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class EmailServiceTest {

	@MockBean
	private AccountsRepo accountRepo;

	@Autowired
	private EmailService emailService;


	@TestConfiguration
	static class EmailServiceContextConfiguration{
		@Bean
		public EmailService emailService(){
			return new EmailService();
		}
	}

	@Test
	public void whenFindAll_ReturnAllAccounts()
	{
		//Mockup
	 	Account account1 = new Account("1", "ramy", "123", "ramy@mail.com", null, null);
	 	Account account2 = new Account("2", "zyad", "321", "zyad@mail.com", null, null);
		List<Account> data = Arrays.asList(account1, account2);

		given(this.accountRepo.findAll()).willReturn(data);

		assertThat(this.emailService.findAll()).hasSize(2).contains(account1, account2);

	}

	@Test
	public void  whenGetEmail_EmailShouldBeFound()
	{
		Account account1 = new Account("1", "ramy", "123", "ramy@mail.com", null, null);
		given(accountRepo.findByEmail(anyString())).willReturn(account1);

		Account result = emailService.findAccount("ramy@mail.com");

		assertThat(result.getPassword()).containsIgnoringCase("123");
	}

	@Test
	public void whenInvalidEmail_EmailShouldNotBeFound()
	{
		given(accountRepo.findByEmail(anyString())).willReturn(null);
		emailService.findAccount("1");
	}


	@Test
	public void whenCreatingMailandEmailHasNoMatches_ReturnTrue()
	{
		Account account1 = new Account("1", "ramy", "123", "ramy@mail.com", null, null);
		Account account2 = new Account("2", "zyad", "321", "zyad@mail.com", null, null);
		List<Account> data = List.of(account1);
		given(accountRepo.findAll()).willReturn(data);

		assertThat(emailService.validate(account2)).isTrue();
	}



}
