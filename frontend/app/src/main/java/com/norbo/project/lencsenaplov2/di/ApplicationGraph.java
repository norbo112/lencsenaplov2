package com.norbo.project.lencsenaplov2.di;

import android.app.Application;
import android.content.Context;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.db.LencseDatabase;
import com.norbo.project.lencsenaplov2.di.controller.ControllerComponent;
import com.norbo.project.lencsenaplov2.di.controller.ControllerModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { FirstModule.class, DataBaseModule.class})
public interface ApplicationGraph {
    void inject(LencsenaploApplication lencsenaploApplication);

    ControllerComponent controllerComponent(ControllerModule controllerModule);

    //export for childs
    Application getApplication();
    LencseDatabase lencseDatabase();
    LencseRepository lencseRepository();
}
