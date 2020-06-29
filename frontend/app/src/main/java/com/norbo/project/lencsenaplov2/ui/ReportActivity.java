package com.norbo.project.lencsenaplov2.ui;

import android.annotation.SuppressLint;
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
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.di.controller.ControllerComponent;
import com.norbo.project.lencsenaplov2.di.controller.ControllerModule;
import com.norbo.project.lencsenaplov2.ui.dialogs.LencseInfoDialog;
import com.norbo.project.lencsenaplov2.ui.utils.FormatUtils;
import com.norbo.project.lencsenaplov2.ui.utils.actions.ReportAction;
import com.norbo.project.lencsenaplov2.ui.utils.report.ReportUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    LencseInfoDialog infoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getControllerComponent().inject(this);
        super.onCreate(savedInstanceState);

        setSupportActionBar(binding.toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel.getLencseData().observe(this, (lencseList) -> {
            if(lencseList != null) {
                binding.setInfo(getInfoMsg(lencseList));
                binding.setAction(new ReportAction(this, lencseList));
                Collections.sort(lencseList,
                        ((o1, o2) -> Long.compare(o1.getBetetelIdopont(), o2.getBetetelIdopont())));
                initChart(binding.chart, lencseList);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private Info getInfoMsg(List<Lencse> lencseList) {
        Info info = new Info();
        info.setCountMsg(lencseList.size()+" db bejegyzés mentve");
        Lencse max = Collections.max(lencseList, getLencseComparator());
        Lencse min = Collections.min(lencseList, getLencseComparator());
//        float elteltIdoMax = dataUtils.elapsedTimeFloat(max.getBetetelIdopont(), max.getKivetelIdopont());
//        float elteltIdoMin = dataUtils.elapsedTimeFloat(min.getBetetelIdopont(), min.getKivetelIdopont());
        float elteltIdoMax = infoDialog.getUtils().elapsedTimeFloat(max.getBetetelIdopont(), max.getKivetelIdopont());
        float elteltIdoMin = infoDialog.getUtils().elapsedTimeFloat(min.getBetetelIdopont(), min.getKivetelIdopont());

        String format = "%s: %s \n%.2f óra és %.2f perc";

        info.setMaxMsg(String.format(format,
                "Legtöbbet viselt nap",
                FormatUtils.getDayShortFormat(max.getBetetelIdopont()),
                (elteltIdoMax/60), (elteltIdoMax % 60), " perc."));

        info.setMinMsg(String.format(format,
                "Legkevesebb nap",
                FormatUtils.getDayShortFormat(min.getBetetelIdopont()),
                (elteltIdoMin/60), (elteltIdoMin % 60), " perc."));
        return info;
    }

    private Comparator<Lencse> getLencseComparator() {
        return (o1, o2) -> {
            long o1_elt = o1.getKivetelIdopont() - o1.getBetetelIdopont();
            long o2_elt = o2.getKivetelIdopont() - o2.getBetetelIdopont();
            return Long.compare(o1_elt, o2_elt);
        };
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
                    infoDialog.getUtils().elapsedTimeFloat(
                            lencseList.get(i).getBetetelIdopont(),
                            lencseList.get(i).getKivetelIdopont()),
                    lencseList.get(i)));
        }
        return entries;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
//        String elteltido = dataUtils.elapsedTime(((Lencse)e.getData()).getBetetelIdopont(),
//                ((Lencse)e.getData()).getKivetelIdopont());
//        Toast.makeText(this,  elteltido, Toast.LENGTH_SHORT).show();
        infoDialog.showDialog("Információ", "Lencse adatai", (Lencse) e.getData());
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

    public class Info {
        private String countMsg;
        private String minMsg;
        private String maxMsg;

        public String getCountMsg() {
            return countMsg;
        }

        public void setCountMsg(String countMsg) {
            this.countMsg = countMsg;
        }

        public String getMinMsg() {
            return minMsg;
        }

        public void setMinMsg(String minMsg) {
            this.minMsg = minMsg;
        }

        public String getMaxMsg() {
            return maxMsg;
        }

        public void setMaxMsg(String maxMsg) {
            this.maxMsg = maxMsg;
        }
    }
}