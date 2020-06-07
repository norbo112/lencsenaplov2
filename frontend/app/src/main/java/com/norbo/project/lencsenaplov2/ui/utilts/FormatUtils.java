package com.norbo.project.lencsenaplov2.ui.utilts;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd. EEE. HH:mm:ss");

    public static String getDateString(long timestamp) {
        return format.format(new Date(timestamp));
    }
}
