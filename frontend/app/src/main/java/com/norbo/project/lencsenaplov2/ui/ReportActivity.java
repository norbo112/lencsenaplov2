package com.norbo.project.lencsenaplov2.ui;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.databinding.ActivityReportBinding;
import com.norbo.project.lencsenaplov2.ui.utils.lencseinfo.LencseInfoUtil;
import com.norbo.project.lencsenaplov2.ui.utils.FormatUtils;
import com.norbo.project.lencsenaplov2.ui.utils.actions.ReportAction;
import com.norbo.project.lencsenaplov2.ui.utils.report.ReportUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class ReportActivity extends BaseActivity<ActivityReportBinding> implements OnChartValueSelectedListener, ReportUI {
    private static final String TAG = "ReportActivity";
    public ReportActivity() {
        super(R.layout.activity_report);
    }

    @Inject
    LencseViewModel viewModel;

    @Inject
    LencseInfoUtil lencseInfoUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getControllerComponent().inject(this);
        super.onCreate(savedInstanceState);

        setSupportActionBar(binding.toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel.getLencseData().observe(this, (lencseList) -> {
            if(lencseList != null) {
                binding.setInfo(lencseInfoUtil.getInfo(lencseList));
                binding.setAction(new ReportAction(this, lencseList));
                Collections.sort(lencseList,
                        ((o1, o2) -> Long.compare(o1.getBetetelIdopont(), o2.getBetetelIdopont())));
                initChart(binding.chart, lencseList);
            }
        });
    }

    private void initChart(LineChart chart, List<Lencse> list) {
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setOnChartValueSelectedListener(this);
        chart.setDrawGridBackground(false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setTextColor(Color.WHITE);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return (int)value / 60 + "h";
            }
        });
        chart.getAxisRight().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setEnabled(true);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setValueFormatter(getMyFormatter(list));

        setChartData(chart, list);
        chart.animateX(1500);

        chart.getLegend().setEnabled(false);
    }

    private IndexAxisValueFormatter getMyFormatter(List<Lencse> list) {
        return new IndexAxisValueFormatter(
                list.stream().map((lencse) -> FormatUtils.getDayShortFormat(lencse.getBetetelIdopont())).collect(Collectors.toList())
        );
    }

    private void setChartData(LineChart lineChart, List<Lencse> lencseList) {
        ArrayList<Entry> entries = getEntries(lencseList);

        LineDataSet set;

        set = getLineDataSet(lineChart, entries);

        LineData data = new LineData(set);
        lineChart.setData(data);
    }

    private LineDataSet getLineDataSet(LineChart lineChart, ArrayList<Entry> entries) {
        LineDataSet set;
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set.setValues(entries);
            set.notifyDataSetChanged();
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            set = new LineDataSet(entries, "Eltelt idő");
            set.setDrawIcons(false);
            set.enableDashedLine(10f, 5f, 0f);
            set.setColor(Color.WHITE);
            set.setCircleColor(Color.GREEN);
            set.setLineWidth(1f);
            set.setCircleRadius(3f);
            set.setCircleHoleRadius(3f);
            set.setFormLineWidth(1f);
            set.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set.setFormSize(15.f);
        }
        return set;
    }

    private ArrayList<Entry> getEntries(List<Lencse> lencseList) {
        ArrayList<Entry> entries = new ArrayList<>(lencseList.size());
        for (int i = 0; i < lencseList.size(); i++) {
            entries.add(new Entry(i,
                    lencseInfoUtil.elapsedTimeFloat(lencseList.get(i)),
                    lencseList.get(i)));
        }
        return entries;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
//        String elteltido = dataUtils.elapsedTime(((Lencse)e.getData()).getBetetelIdopont(),
//                ((Lencse)e.getData()).getKivetelIdopont());
//        Toast.makeText(this,  elteltido, Toast.LENGTH_SHORT).show();
        lencseInfoUtil.showDialog("Információ", "Lencse adatai", (Lencse) e.getData());
    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void updateLineChart(List<Lencse> lencseList) {
        binding.chart.clear();
        binding.chart.getXAxis().setValueFormatter(getMyFormatter(lencseList));
        binding.chart.setData(new LineData(getLineDataSet(binding.chart, getEntries(lencseList))));
        binding.chart.fitScreen();
        binding.chart.notifyDataSetChanged();
        binding.chart.invalidate();
    }
}