package com.norbo.project.lencsenaplov2.ui.utils.actions;

import android.app.Activity;

import androidx.databinding.BaseObservable;

import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.di.controller.ControllerComponent;
import com.norbo.project.lencsenaplov2.di.controller.ControllerModule;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.utils.MyToaster;

import javax.inject.Inject;

public class Action extends BaseObservable {
    protected Activity context;

    @Inject
    LencseViewModel lencseViewModel;

    @Inject
    MyToaster myToaster;

    public Action(Activity context) {
        this.context = context;
        getCompontent().inject(this);
    }

    protected ControllerComponent getCompontent() {
        return ((LencsenaploApplication)context.getApplicationContext()).getGraph().controllerComponent(new ControllerModule(context));
    }
}
