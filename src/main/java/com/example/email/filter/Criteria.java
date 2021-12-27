package com.example.email.filter;

import com.example.email.Mail;

import java.util.List;

public interface Criteria {
    public List<Mail> meetCriteria(List<Mail> mails, Mail criteria);
}
