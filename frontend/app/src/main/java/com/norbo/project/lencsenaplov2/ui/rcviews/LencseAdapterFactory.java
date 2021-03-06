package com.norbo.project.lencsenaplov2.ui.rcviews;

import android.app.Activity;
import android.content.Context;

import com.norbo.project.lencsenaplov2.data.model.Lencse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LencseAdapterFactory {
    @Inject
    public LencseAdapterFactory() {
    }

    public LencseAdapter create(Activity context, List<Lencse> lencseList) {
        return new LencseAdapter(lencseList, context);
    }
}
