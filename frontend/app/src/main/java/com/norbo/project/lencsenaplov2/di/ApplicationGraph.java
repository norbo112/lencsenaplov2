package com.norbo.project.lencsenaplov2.di;

import android.content.Context;

import com.norbo.project.lencsenaplov2.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FirstModule.class)
public interface ApplicationGraph {
    void inject(LencsenaploApplication lencsenaploApplication);
    void inject(MainActivity mainActivity);
    Context getContext();
}
