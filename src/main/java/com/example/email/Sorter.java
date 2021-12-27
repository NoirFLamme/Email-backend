package com.example.email;

import com.example.email.sort.Sort;
import com.example.email.sort.SortByDate;
import com.example.email.sort.SortByImportance;
import com.example.email.sort.SortByOpened;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter {
    List<Mail> mails;


    public Sorter(List<Mail> mails)
    {
        this.mails = mails;
    }

    public void sortby(String sort)
    {
        switch (sort){
            case "dateUp":
                Collections.sort(mails, new SortByDate());
                break;
            case  "dateDown":
                Collections.sort(mails, new SortByDate().reversed());
                break;
            case "importance":
                Collections.sort(mails, new SortByImportance().reversed());
                break;
            case "opened":
                System.out.println("yes");
                Collections.sort(mails, new SortByOpened());
                break;
        }
    }
}
