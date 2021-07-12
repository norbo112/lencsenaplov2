package com.norbo.project.lencsenaplov2.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;

import androidx.lifecycle.LifecycleOwner;

import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.ui.utils.DataUtils;

import java.time.Instant;
import java.time.LocalDateTime;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Implementation of App Widget functionality.
 */
@AndroidEntryPoint
public class LenceLogAppWidget extends AppWidgetProvider {

    @Inject
    LencseRepository lencseRepository;

    private DataUtils dataUtils;

    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Cursor kezdoIdopont = lencseRepository.selectKezdoIdopontCursor();

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.lence_log_app_widget);
        views.setTextViewText(R.id.appwidget_text, getElteltIdo(kezdoIdopont));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private String getElteltIdo(Cursor cursor) {
        Long lg = null;
        while (cursor.moveToNext()) {
            lg = cursor.getLong(0);
        }
        dataUtils = new DataUtils();
        String s;

        if(lg != null) {
            return dataUtils.elapsedTime(lg, System.currentTimeMillis());
        }

        return "Nincs adat";
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}