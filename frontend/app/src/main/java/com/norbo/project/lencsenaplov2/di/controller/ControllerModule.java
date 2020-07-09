package com.norbo.project.lencsenaplov2.di.controller;

import android.content.Context;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.utils.actions.MainAction;
import com.norbo.project.lencsenaplov2.ui.utils.actions.ReportAction;
import com.norbo.project.lencsenaplov2.ui.utils.lencseinfo.LencseInfoUtil;
import com.norbo.project.lencsenaplov2.ui.rcviews.LencseAdapterFactory;
import com.norbo.project.lencsenaplov2.ui.utils.ConvertEntities;
import com.norbo.project.lencsenaplov2.ui.utils.DataUtils;
import com.norbo.project.lencsenaplov2.ui.utils.LencseAdatToltoController;
import com.norbo.project.lencsenaplov2.ui.utils.MyToaster;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;

@Module
@InstallIn(ActivityComponent.class)
public class ControllerModule {

    @Provides
    LencseInfoUtil lencseInfoDialog(@ActivityContext Context context, DataUtils dataUtils) {
        return new LencseInfoUtil(context, dataUtils);
    }

    @Provides
    LencseAdapterFactory provideAdapterFactory(@ActivityContext Context context, DataUtils dataUtils) {
        return new LencseAdapterFactory(context, dataUtils);
    }

    @Provides
    LencseAdatToltoController provideAdatTolto(@ActivityContext Context context, LencseViewModel viewModel, MyToaster toaster) {
        return new LencseAdatToltoController(context, viewModel, toaster);
    }

    @Provides
    MyToaster provideMyToaster(@ActivityContext Context context) {
        return new MyToaster(context);
    }

    @Provides
    DataUtils provideDataUtils() {
        return new DataUtils();
    }

    @Provides
    MainAction mainAction(@ActivityContext Context context, LencseViewModel viewModel, MyToaster myToaster) {
        return new MainAction(context, viewModel, myToaster);
    }

    @Provides
    ReportAction reportAction(@ActivityContext Context context, MyToaster myToaster) {
        return new ReportAction(context, myToaster);
    }
}
