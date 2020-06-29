package com.norbo.project.lencsenaplov2.di.controller;

import com.norbo.project.lencsenaplov2.di.PerActivity;
import com.norbo.project.lencsenaplov2.ui.MainActivity;
import com.norbo.project.lencsenaplov2.ui.ReportActivity;
import com.norbo.project.lencsenaplov2.ui.rcviews.LencseAdapter;
import com.norbo.project.lencsenaplov2.ui.utils.actions.Action;
import com.norbo.project.lencsenaplov2.ui.utils.actions.MainAction;
import com.norbo.project.lencsenaplov2.ui.utils.actions.ReportAction;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ControllerModule.class)
public interface ControllerComponent {
    void inject(ReportActivity reportActivity);

    void inject(MainActivity mainActivity);

    void inject(LencseAdapter lencseAdapter);

    void inject(Action action);
}
