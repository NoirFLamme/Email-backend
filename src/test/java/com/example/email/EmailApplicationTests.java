package com.example.email;
import com.example.email.objects.Account;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
class EmailApplicationTests {

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
	public void whenFindAll_Return()
	{
		//Mockup
	 	Account account1 = new Account("1", "ramy", "123", "ramy@mail.com", null, null);
	 	Account account2 = new Account("2", "zyad", "321", "zyad@mail.com", null, null);
		List<Account> data = Arrays.asList(account1, account2);

		given(this.accountRepo.findAll()).willReturn(data);

		assertThat(emailService.findAll()).hasSize(2).contains(account1, account2);

	}

}
