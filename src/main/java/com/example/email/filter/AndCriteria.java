package com.example.email.filter;

import com.example.email.objects.Mail;

import java.util.ArrayList;
import java.util.List;

public class AndCriteria implements Criteria{
    private Criteria date;
    private Criteria receiver;
    private Criteria sender;
    private Criteria type;

    public AndCriteria(Criteria date, Criteria receiver, Criteria sender, Criteria type) {
        this.date = date;
        this.receiver = receiver;
        this.sender = sender;
        this.type = type;
    }

    @Override
    public List<Mail> meetCriteria(List<Mail> mails, Mail criteria)
    {
        List<Mail> filteredMails = new ArrayList<Mail>();
        if (criteria.getDate() != null)
        {
            filteredMails = date.meetCriteria(mails, criteria);
        }
        if (criteria.getReceivers() != null)
        {
            filteredMails = receiver.meetCriteria(mails, criteria);
        }
        if (criteria.getSender() != null)
        {
            filteredMails = sender.meetCriteria(mails, criteria);
        }
        if (criteria.getType() != null)
        {
            filteredMails = type.meetCriteria(mails, criteria);
        }
        return filteredMails;
    }
}
