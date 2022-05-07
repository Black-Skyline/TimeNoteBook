package com.example.timenotebook.databases_about;

import android.annotation.SuppressLint;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GetTime {
    @SuppressLint("SimpleDateFormat")
    public static String getTime(String display_y) {
        DateFormat dateFormat;
        if (display_y.equals("OK")) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        }
        else {
            dateFormat = new SimpleDateFormat("MM-dd hh:mm");
        }
        return dateFormat.format(new Date());
    }
}
