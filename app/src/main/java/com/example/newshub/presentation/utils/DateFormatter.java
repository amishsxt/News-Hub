package com.example.newshub.presentation.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    public static String formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("MMMM d'th' yyyy", Locale.ENGLISH);
        return formatter.format(date);
    }
}
