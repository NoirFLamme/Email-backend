package com.example.email.filter;

import com.example.email.objects.Mail;

import java.util.ArrayList;
import java.util.List;

public class CriteriaDate implements Criteria
{
    @Override
    public List<Mail> meetCriteria(List<Mail> mails, Mail criteria)
    {
        List<Mail> matchedMails = new ArrayList<Mail>();
        for (Mail mail:
                mails) {
            if (mail.getDate().equals(criteria.getDate()))
            {
                matchedMails.add(mail);
            }
        }
        return matchedMails;
    }
}
