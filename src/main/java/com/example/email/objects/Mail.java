package com.example.email.objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


public class Mail implements Comparable<Mail>{

    private  int id;

    private final Contact sender;
    private final List<Contact> receivers;
    private final Date date;
    private final String subject;
    private final String body;
    private List<Attachment> attachment;
    private String type;
    private int opened;
    private int  starred;
    private final Integer priority;

    public Mail(Contact sender,Date date, List<Contact> receivers, String subject,
                String body, int id, String type, Integer priority, List<Attachment>  attachment) {
        this.sender = sender;
        this.receivers = receivers;
        this.date = this.getDate();
        this.subject = subject;
        this.body = body;
        this.id = id;
        this.type = type;
        this.opened = 0;
        this.starred = 0;
        this.priority = priority;
        this.attachment = attachment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact getSender() {
        return sender;
    }

    public List<Contact> getReceivers() {
        return receivers;
    }

    public Date getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }


    public String getType() {
        return type;
    }

    public Mail setType(String type) {
        this.type = type;
        return this;
    }

    public int isOpened() {
        return opened;
    }

    public void setOpened(int opened) {
        this.opened = opened;
    }

    public int isStarred() {
        return  this.starred;
    }

    public void setStarred(int starred) {
        this.starred = starred;
    }

    public int compareTo(Mail mail) {
        return this.date.compareTo(mail.date);
    }

    public Integer getPriority() {
        return priority;
    }

    public List<Attachment>  getAttachment() {
        return attachment;
    }

    public void setAttachment(List<Attachment>  attachment) {
        this.attachment = attachment;
    }
}