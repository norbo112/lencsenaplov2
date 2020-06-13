package com.norbo.project.lencsenaplov2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.databinding.ActivityReportBinding;
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.ui.utilts.DataUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ReportActivity extends BaseActivity<ActivityReportBinding> implements OnChartValueSelectedListener {
    public ReportActivity() {
        super(R.layout.activity_report);
    }

    @Inject
    LencseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((LencsenaploApplication)getApplicationContext()).getGraph().inject(this);
        super.onCreate(savedInstanceState);

        setSupportActionBar(binding.toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel.getLencseData().observe(this, (lencseList) -> {
            initChart(binding.chart, lencseList);
        });
    }

    private void initChart(LineChart chart, List<Lencse> list) {
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setOnChartValueSelectedListener(this);
        chart.setDrawGridBackground(false);

        setCharData(chart, list);
        chart.animateX(1500);
        Legend l = chart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
    }

    private void setCharData(LineChart lineChart, List<Lencse> lencseList) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < lencseList.size(); i++) {
            entries.add(new Entry(i,
                    DataUtils.elapsedTimeFloat(
                            lencseList.get(i).getBetetelIdopont(),
                            lencseList.get(i).getKivetelIdopont()), lencseList.get(i)));
        }

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
            set.setColor(Color.BLACK);
            set.setLineWidth(1f);
            set.setCircleRadius(3f);
            set.setCircleHoleRadius(3f);
            set.setFormLineWidth(1f);
            set.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set.setFormSize(15.f);
        }

        LineData data = new LineData(set);
        lineChart.setData(data);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        String elteltido = DataUtils.elapsedTime(((Lencse)e.getData()).getBetetelIdopont(),
                ((Lencse)e.getData()).getKivetelIdopont());
        Toast.makeText(this,  "Eltelt idő: "+elteltido, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }
}