package com.example.email.objects;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Mail> {
    public int compare(Mail a, Mail b)
    {
        return a.getPriority() - b.getPriority();
    }
}
