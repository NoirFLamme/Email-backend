package com.example.email.filter;

import com.example.email.Mail;

import java.util.ArrayList;
import java.util.List;

public class CriteriaType implements Criteria {

    @Override
    public List<Mail> meetCriteria(List<Mail> mails, Mail criteria)
    {
        List<Mail> matchedMails = new ArrayList<Mail>();
        for (Mail mail:
                mails) {
            if (mail.getType().equals(criteria.getType()))
            {
                matchedMails.add(mail);
            }
        }
        return matchedMails;
    }
}
