package com.norbo.project.lencsenaplov2.ui.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Toastokat jelenít meg az UI-n
 */
@Singleton
public class MyToaster {
    Context context;
    private Handler handler = new Handler();

    @Inject
    public MyToaster(Context context) {
        this.context = context;
    }

    public void show(String msg) {
        handler.post(() -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
    }
}
