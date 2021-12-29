package com.example.email.sort;

import com.example.email.objects.Mail;

import java.util.Comparator;

public class SortByOpened implements Comparator<Mail>, Sort {
    public int compare(Mail a, Mail b){
        System.out.println(a.isOpened() - b.isOpened());
        return a.isOpened() - b.isOpened();
    }
}
