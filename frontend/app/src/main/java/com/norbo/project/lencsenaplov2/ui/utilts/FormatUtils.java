package com.norbo.project.lencsenaplov2.ui.utilts;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd. EEE. HH:mm:ss");
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd. EEE.");
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dayshortFORMAT = new SimpleDateFormat("MM.dd.");
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");


    public static String getDateString(long timestamp) {
        if(timestamp == 0) {
            return "Nincs időpont rögzítve";
        }
        return format.format(new Date(timestamp));
    }

    public static String getTimeString(long timestamp) {
        if(timestamp == 0) {
            return "Nincs időpont rögzítve";
        }
        return timeFormat.format(new Date(timestamp));
    }

    public static String getDayString(long timestamp) {
        if(timestamp == 0) {
            return "Nincs időpont rögzítve";
        }
        return dateFormat.format(new Date(timestamp));
    }

    public static String getDayShortFormat(long timestamp) {
        return dayshortFORMAT.format(new Date(timestamp));
    }
}
