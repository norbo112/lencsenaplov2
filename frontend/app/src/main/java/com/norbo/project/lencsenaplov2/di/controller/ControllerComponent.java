package com.norbo.project.lencsenaplov2.di.controller;

import com.norbo.project.lencsenaplov2.di.ApplicationGraph;
import com.norbo.project.lencsenaplov2.di.PerActivity;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.ReportActivity;
import com.norbo.project.lencsenaplov2.ui.dialogs.LencseInfoDialog;

import dagger.Component;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ControllerModule.class)
public interface ControllerComponent {
    void inject(ReportActivity reportActivity);
}
