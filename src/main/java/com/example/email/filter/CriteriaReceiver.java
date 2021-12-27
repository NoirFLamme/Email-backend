package com.example.email.filter;

import com.example.email.Contact;
import com.example.email.Mail;

import java.util.ArrayList;
import java.util.List;

public class CriteriaReceiver implements Criteria{

    @Override
    public List<Mail> meetCriteria(List<Mail> mails, Mail criteria)
    {
        List<Mail> matchedMails = new ArrayList<Mail>();
        for (Mail mail:
                mails) {
            for (Contact contact:
                 mail.getReceivers()) {
                if (contact.getName().equals(criteria.getReceivers()[0].getName()) ||
                        contact.getMailAddresses().equals(criteria.getReceivers()[0].getMailAddresses()))
                {
                    matchedMails.add(mail);
                    break;
                }
            }

        }
        return matchedMails;
    }
}
