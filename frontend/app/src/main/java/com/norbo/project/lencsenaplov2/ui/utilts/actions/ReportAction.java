package com.norbo.project.lencsenaplov2.ui.utilts.actions;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.ui.ReportActivity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class ReportAction {
    private Context context;
    private String startDate;
    private String endDate;
    private List<Lencse> origLencseList;
    private List<Lencse> lencseList;

    public ReportAction(Context context) {
        this.context = context;
    }

    public ReportAction(Context context, List<Lencse> lencseList) {
        this.context = context;
        this.lencseList = lencseList;
        this.origLencseList = lencseList;
    }

    public void showStartDateSetter() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) ->
                lencseList = origLencseList.stream().filter(lencse -> {
                    LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(lencse.getBetetelIdopont()), ZoneId.of("GMT+02:00"));
                    setStartDate(dateTime.toLocalDate().toString());
                    return (dateTime.getYear() >= year && dateTime.getMonthValue() >= month && dateTime.getDayOfMonth() >= dayOfMonth);
                }).collect(Collectors.toList()), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showEndtDateSetter() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) ->
                lencseList = origLencseList.stream().filter(lencse -> {
                    LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(lencse.getBetetelIdopont()), ZoneId.of("GMT+02:00"));
                    setEndDate(dateTime.toLocalDate().toString());
                    return (dateTime.getYear() <= year && dateTime.getMonthValue() <= month && dateTime.getDayOfMonth() <= dayOfMonth);
                }).collect(Collectors.toList()), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
