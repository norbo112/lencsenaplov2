package com.norbo.project.lencsenaplov2.ui.utils.actions;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.utils.MyToaster;

public class Action extends BaseObservable {
    protected Context context;
    protected LencseViewModel lencseViewModel;
    protected MyToaster myToaster;

    public Action(Context context, LencseViewModel lencseViewModel, MyToaster myToaster) {
        this.context = context;
        this.lencseViewModel = lencseViewModel;
        this.myToaster = myToaster;
    }

    public Context getContext() {
        return context;
    }

    public LencseViewModel getLencseViewModel() {
        return lencseViewModel;
    }

    public MyToaster getMyToaster() {
        return myToaster;
    }
}
