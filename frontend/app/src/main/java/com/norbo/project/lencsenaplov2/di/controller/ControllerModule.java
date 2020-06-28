package com.norbo.project.lencsenaplov2.di.controller;

import android.app.Activity;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.dialogs.LencseInfoDialog;
import com.norbo.project.lencsenaplov2.ui.utils.ConvertEntities;
import com.norbo.project.lencsenaplov2.ui.utils.DataUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {
    private Activity activity;

    public ControllerModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity getActivity() {
        return activity;
    }

    @Provides
    LencseViewModel lencseViewModel(LencseRepository repository, ConvertEntities convertEntities) {
        return new LencseViewModel(repository, convertEntities);
    }

    @Provides
    LencseInfoDialog lencseInfoDialog(Activity activity, DataUtils dataUtils, LencseViewModel viewModel) {
        return new LencseInfoDialog(activity, dataUtils, viewModel);
    }
}
