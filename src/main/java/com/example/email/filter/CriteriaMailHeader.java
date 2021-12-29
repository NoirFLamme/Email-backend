package com.example.email.filter;

import com.example.email.objects.Mail;

import java.util.ArrayList;
import java.util.List;

public class CriteriaMailHeader{
    public List<Mail> meetCriteria(List<Mail> mails, String criteria)
    {
        List<Mail> matchedMails = new ArrayList<Mail>();
        for (Mail mail:
             mails) {
            if (mail.getSubject().equals(criteria))
            {
                matchedMails.add(mail);
            }
        }
        return matchedMails;
    }
}

