package com.norbo.project.lencsenaplov2.ui.utils.actions;

import android.app.TimePickerDialog;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.norbo.project.lencsenaplov2.data.model.KezdoIdopont;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.utils.MyToaster;
import com.norbo.project.lencsenaplov2.ui.utils.UpdateLencseUI;
import com.norbo.project.lencsenaplov2.widget.LenceLogAppWidget;

import java.util.Calendar;
import java.util.Date;

public class MainAction extends BaseObservable {
    private static final String TAG = MainAction.class.getSimpleName();
    private final Context context;
    private final LencseViewModel lencseViewModel;
    private final MyToaster myToaster;
    private UpdateLencseUI updateLencseUI;

    public MainAction(Context context, LencseViewModel lencseViewModel, MyToaster myToaster) {
        this.context = context;
        this.lencseViewModel = lencseViewModel;
        this.myToaster = myToaster;
        this.updateLencseUI = (UpdateLencseUI) context;
    }

    public void betesz(MutableLiveData<Lencse> lencseMutableLiveData) {
        Lencse value = lencseMutableLiveData.getValue();
        if(value != null && value.getBetetelIdopont() == 0) {
            value.setBetetelIdopont(System.currentTimeMillis());
            lencseMutableLiveData.postValue(value);

            lencseViewModel.insertKezdoIdopont(new KezdoIdopont(value.getBetetelIdopont()));
            Log.i(TAG, "betesz: lefutott");
            updateWidget();
        }
    }

    public void kivesz(MutableLiveData<Lencse> lencseMutableLiveData) {
        Lencse value = lencseMutableLiveData.getValue();
        if(value != null && value.getKivetelIdopont() == 0 && value.getBetetelIdopont() != 0) {
            value.setKivetelIdopont(System.currentTimeMillis());

            lencseMutableLiveData.postValue(value);
            lencseViewModel.insert(value).whenComplete((id, throwable) -> myToaster.show("Lencseadat rögzítve: "+id));
            lencseViewModel.deleteKezdoIdopont(value.getBetetelIdopont());
            updateLencseUI.clearLencseUi();

            updateWidget();
        }

        Log.i(TAG, "kivesz: lefutott");
    }

    public void setLencseUjIdopont(MutableLiveData<Lencse> lencseadat) {
        final Lencse value = lencseadat.getValue();
        if(value == null || value.getBetetelIdopont() == 0) {
            return;
        }

        final Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, (view, hourOfDay, minute) -> {
            date.setHours(hourOfDay);
            date.setMinutes(minute);
            lencseViewModel.deleteKezdoIdopont(value.getBetetelIdopont());
            lencseViewModel.insertKezdoIdopont(new KezdoIdopont(date.getTime()));
            value.setBetetelIdopont(date.getTime());
            lencseadat.postValue(value);
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        timePickerDialog.show();
    }

    public void setTisztitoViz(MutableLiveData<Lencse> lencseAdat) {
        //ezt itt biztosan másképp kellene megoldanom, csak flippelem az értéket, függetlenül hogy kivan e pipálva vagy nincs....
        final Lencse lencse = lencseAdat.getValue();
        if(lencse != null) {
            if(lencse.getTisztitoViz() == 0) lencse.setTisztitoViz(1);
            else lencse.setTisztitoViz(0);

            lencseAdat.postValue(lencse);
            Log.i(TAG, "setTisztitoViz: lencse állítva: "+lencse.getTisztitoViz());
        } else {
            Log.i(TAG, "setTisztitoViz: Lencse Adat null");
        }
    }

    public LencseViewModel getLencseViewModel() {
        return lencseViewModel;
    }

    public void updateWidget() {
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context, LenceLogAppWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        context.sendBroadcast(intent);
    }
}
