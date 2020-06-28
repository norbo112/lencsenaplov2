package com.norbo.project.lencsenaplov2.di;

import android.app.Application;
import android.content.Context;

import com.norbo.project.lencsenaplov2.di.controller.ControllerComponent;
import com.norbo.project.lencsenaplov2.di.controller.ControllerModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FirstModule.class)
public interface ApplicationGraph {
    void inject(LencsenaploApplication lencsenaploApplication);

    ControllerComponent controllerComponent(ControllerModule controllerModule);

    Application getApplication();
}
