package com.norbo.project.lencsenaplov2.di;

import android.app.Application;

public class LencsenaploApplication extends Application {
    private ApplicationGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = DaggerApplicationGraph.builder()
                .firstModule(new FirstModule(this))
                .build();
        graph.inject(this);
    }

    public ApplicationGraph getGraph() {
        return graph;
    }
}
