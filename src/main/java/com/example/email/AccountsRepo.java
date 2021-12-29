package com.example.email;

import com.example.email.objects.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountsRepo extends MongoRepository<Account, String> {
    Account findByEmail(String email);

}
