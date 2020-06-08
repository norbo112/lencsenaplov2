package com.norbo.project.lencsenaplov2.ui.utilts.actions;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.norbo.project.lencsenaplov2.data.model.KezdoIdopont;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.utilts.ClearLencse;

import javax.inject.Inject;

public class MainAction {
    private static final String TAG = MainAction.class.getSimpleName();
    private ClearLencse clearLencse;

    public MainAction(Context context) {
        ((LencsenaploApplication)context.getApplicationContext()).getGraph().inject(this);
        this.clearLencse = (ClearLencse) context;
    }

    @Inject
    LencseViewModel lencseViewModel;

    public void betesz(MutableLiveData<Lencse> lencseMutableLiveData) {
        Lencse value = lencseMutableLiveData.getValue();
        if(value != null && value.getBetetelIdopont() == 0) {
            value.setBetetelIdopont(System.currentTimeMillis());
            lencseMutableLiveData.postValue(value);

            lencseViewModel.insertKezdoIdopont(new KezdoIdopont(value.getBetetelIdopont()));
            Log.i(TAG, "betesz: lefutott");
        } else  {
            return;
        }

    }

    public void kivesz(MutableLiveData<Lencse> lencseMutableLiveData) {
        Lencse value = lencseMutableLiveData.getValue();
        if(value != null && value.getKivetelIdopont() == 0 && value.getBetetelIdopont() != 0) value.setKivetelIdopont(System.currentTimeMillis());
        else return;
        lencseMutableLiveData.postValue(value);
        lencseViewModel.insert(value);
        lencseViewModel.deleteKezdoIdopont(value.getBetetelIdopont());
        clearLencse.clearLencseUi();
        Log.i(TAG, "kivesz: lefutott");
    }
}
