package com.norbo.project.lencsenaplov2.ui.utilts.actions;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.PrecomputedText;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.ui.utilts.MyToaster;
import com.norbo.project.lencsenaplov2.ui.utilts.report.ReportUI;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class ReportAction extends BaseObservable {
    private static final String TAG = "ReportAction";
    private Context context;
    private long startDate;
    private long endDate;
    private List<Lencse> origLencseList;
    private List<Lencse> lencseList;
    private ReportUI reportUI;

    @Inject
    MyToaster toaster;

    public ReportAction(Context context) {
        this.context = context;
    }

    public ReportAction(Context context, List<Lencse> lencseList) {
        this.context = context;
        this.lencseList = lencseList;
        this.origLencseList = lencseList;
        this.reportUI = (ReportUI) context;
        Collections.sort(lencseList, (o1,o2)-> Long.compare(o1.getBetetelIdopont(), o2.getBetetelIdopont()));
        ((LencsenaploApplication)context.getApplicationContext()).getGraph().inject(this);
    }

    public void showStartDateSetter() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(lencseList.get(0).getBetetelIdopont()));
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setStartDate(calendar.getTimeInMillis());
            Log.i(TAG, "showStartDateSetter: "+new Date(startDate));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showEndtDateSetter() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(lencseList.get(lencseList.size() - 1).getBetetelIdopont()));
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setEndDate(calendar.getTimeInMillis());
            Log.i(TAG, "showEndtDateSetter: "+new Date(endDate));
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void filter() {
        if(startDate == 0 || endDate == 0) {
            toaster.show("Kérlek válaszd ki kezdő és végpontokat");
            return;
        }
        List<Lencse> collect = origLencseList.stream()
                .filter((lencse -> lencse.getBetetelIdopont() >= startDate && lencse.getBetetelIdopont() <= endDate))
                .collect(Collectors.toList());
        reportUI.updateLineChart(collect);
    }

    public void clearFilter() {
        if(startDate == 0 || endDate == 0) return;
        setStartDate(0);
        setEndDate(0);
        reportUI.updateLineChart(origLencseList);
    }

    @Bindable
    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
        notifyPropertyChanged(BR.startDate);
    }

    @Bindable
    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
        notifyPropertyChanged(BR.endDate);
    }
}
