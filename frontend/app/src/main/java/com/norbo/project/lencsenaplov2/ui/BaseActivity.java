package com.norbo.project.lencsenaplov2.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.norbo.project.lencsenaplov2.di.ApplicationGraph;
import com.norbo.project.lencsenaplov2.di.FirstModule;

public class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    protected static final String LENCSE_SAVED_KEY = "com.norbo.project.lencsenaplov2.LENCSE_SAVED_KEY";
    private final int layout_id;
    protected T binding;

    private ApplicationGraph graph;

    public BaseActivity(int layout_id) {
        this.layout_id = layout_id;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getGraph().inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), layout_id, null, false);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(binding.getRoot());
    }

    protected ApplicationGraph getGraph() {
        if(graph == null) {
            graph = DaggerApplicationGraph.builder()
                    .firstModule(new FirstModule(this))
                    .build();
        }
        return graph;
    }
}
