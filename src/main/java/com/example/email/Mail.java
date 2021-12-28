package com.example.email;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Mail implements Comparable<Mail>{
    @Id
    private final String id;

    private final Contact sender;
    private final Contacts receivers;
    private final Date date;
    private final String subject;
    private final String body;
    private final Attachment attachment;
    private String type;
    private int opened;
    private int  starred;
    private final int priority;

    public Mail(Contact sender,Date date, Contacts receivers, String subject, String body, Attachment attachment, String id, String type, int priority) {
        this.sender = sender;
        this.receivers = receivers;
        this.date = this.getDate();
        this.subject = subject;
        this.body = body;
        this.attachment = attachment;
        this.id = id;
        this.type = type;
        this.opened = 0;
        this.starred = 0;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public Contact getSender() {
        return sender;
    }

    public Contacts getReceivers() {
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

    public Attachment getAttachment() {
        return attachment;
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

    public int getPriority() {
        return priority;
    }
}