package com.norbo.project.lencsenaplov2.di;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.norbo.project.lencsenaplov2.di.controller.ControllerComponent;
import com.norbo.project.lencsenaplov2.di.controller.ControllerModule;
import com.norbo.project.lencsenaplov2.ui.BaseActivity;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.MainActivity;
import com.norbo.project.lencsenaplov2.ui.rcviews.LencseAdapter;
import com.norbo.project.lencsenaplov2.ui.utils.actions.MainAction;
import com.norbo.project.lencsenaplov2.ui.utils.actions.ReportAction;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FirstModule.class)
public interface ApplicationGraph {
    void inject(LencsenaploApplication lencsenaploApplication);

    ControllerComponent controllerComponent(ControllerModule controllerModule);
    Context getContext();

    void inject(MainAction mainAction);

    void inject(ReportAction reportAction);

    void inject(LencseAdapter lencseAdapter);

    <T extends ViewDataBinding> void inject(BaseActivity<T> tBaseActivity);

//    void inject(ReportActivity reportActivity);

//    void inject(LencseInfoDialog lencseInfoDialog);

}
