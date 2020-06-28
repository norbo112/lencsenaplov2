package com.norbo.project.lencsenaplov2.di.controller;

import android.app.Activity;

import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.ReportActivity;
import com.norbo.project.lencsenaplov2.ui.dialogs.LencseInfoDialog;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Subcomponent(modules = ControllerModule.class)
public interface ControllerComponent {
    void inject(ReportActivity reportActivity);
    LencseInfoDialog lencseInfoDialog();
    LencseViewModel lencseViewModel();
}
