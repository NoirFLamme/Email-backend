package com.example.email;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountsRepo extends MongoRepository<Account, String> {
    Account findByEmail(String email);

}
