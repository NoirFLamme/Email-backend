package com.example.email.sort;

import com.example.email.objects.Mail;

import java.util.Comparator;

public class SortByImportance implements Comparator<Mail> , Sort {
    public int compare(Mail a, Mail b){
            return a.isStarred() - b.isStarred();
    }
}
