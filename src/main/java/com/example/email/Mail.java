package com.example.email;

import java.util.Date;

public class Mail {
    private final int id;
    private final Contact sender;
    private final Contact[] receivers;
    private final Date date;
    private final String subject;
    private final String body;
    private final Attachment attachment;
    private String type;

    public Mail(Contact sender, Contact[] receivers, String subject, String body, Attachment attachment, int id, String type) {
        this.sender = sender;
        this.receivers = receivers;
        this.date = this.getDate();
        this.subject = subject;
        this.body = body;
        this.attachment = attachment;
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Contact getSender() {
        return sender;
    }

    public Contact[] getReceivers() {
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


}