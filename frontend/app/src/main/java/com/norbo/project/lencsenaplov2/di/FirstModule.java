package com.norbo.project.lencsenaplov2.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirstModule {
    public final Application application;

    public FirstModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application getApplication() {
        return application;
    }
}
