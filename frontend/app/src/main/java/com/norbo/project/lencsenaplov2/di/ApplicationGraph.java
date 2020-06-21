package com.norbo.project.lencsenaplov2.di;

import android.content.Context;

import com.norbo.project.lencsenaplov2.ui.rcviews.LencseAdapter;
import com.norbo.project.lencsenaplov2.ui.utilts.LencseAdatToltoController;
import com.norbo.project.lencsenaplov2.ui.MainActivity;
import com.norbo.project.lencsenaplov2.ui.ReportActivity;
import com.norbo.project.lencsenaplov2.ui.utilts.actions.MainAction;
import com.norbo.project.lencsenaplov2.ui.utilts.actions.ReportAction;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FirstModule.class)
public interface ApplicationGraph {
    void inject(LencsenaploApplication lencsenaploApplication);
    void inject(MainActivity mainActivity);
    Context getContext();

    void inject(MainAction mainAction);

    void inject(ReportActivity reportActivity);

    void inject(LencseAdatToltoController adatTolto);

    void inject(ReportAction reportAction);

    void inject(LencseAdapter lencseAdapter);
}
