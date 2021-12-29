package com.example.email.objects;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "attachments")
public class Attachment {

    private int value;
    private String name;
    private String url;

    public Attachment(String name, String url, int value) {
        this.name = name;
        this.url = url;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}