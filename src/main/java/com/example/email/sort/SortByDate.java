package com.example.email.sort;

import com.example.email.Mail;

import java.util.Comparator;

public class SortByDate implements Comparator<Mail>, Sort {

    public int compare(Mail a, Mail b) {
        return a.getDate().compareTo(b.getDate());
    }

}
