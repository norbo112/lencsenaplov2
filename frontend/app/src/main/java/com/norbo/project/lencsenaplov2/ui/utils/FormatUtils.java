package com.norbo.project.lencsenaplov2.ui.utils;

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
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat dateFormatShot = new SimpleDateFormat("MM.dd. EEE.");



    public static String getDateString(long timestamp) {
        if(timestamp == 0) {
            return "N/A";
        }
        return format.format(new Date(timestamp));
    }

    public static String getDateShortFormat(long timestamp) {
        if(timestamp == 0) {
            return "N/A";
        }
        return dateFormatShot.format(new Date(timestamp));
    }

    public static String getTimeString(long timestamp) {
        if(timestamp == 0) {
            return "N/A";
        }
        return timeFormat.format(new Date(timestamp));
    }

    public static String getDayString(long timestamp) {
        if(timestamp == 0) {
            return "N/A";
        }
        return dateFormat.format(new Date(timestamp));
    }

    public static String getDayShortFormat(long timestamp) {
        if(timestamp == 0) {
            return "N/A";
        }
        return dayshortFORMAT.format(new Date(timestamp));
    }
}
