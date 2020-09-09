package com.norbo.project.lencsenaplov2.ui.rcviews;

import android.content.Context;

import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.ui.utils.DataUtils;

import java.util.List;

public class LencseAdapterFactory {
    private final Context context;
    private final DataUtils dataUtils;

    public LencseAdapterFactory(Context context, DataUtils dataUtils) {
        this.context = context;
        this.dataUtils = dataUtils;
    }

    public LencseAdapter create(List<Lencse> lencseList) {
        return new LencseAdapter(lencseList, context, dataUtils);
    }
}
