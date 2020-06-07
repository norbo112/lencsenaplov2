package com.norbo.project.lencsenaplov2.ui.utilts.actions;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;

import javax.inject.Inject;

public class MainAction implements Actioner<Lencse> {
    private static final String TAG = MainAction.class.getSimpleName();

    public MainAction(Context context) {
        ((LencsenaploApplication)context.getApplicationContext()).getGraph().inject(this);
    }

    @Inject
    LencseViewModel lencseViewModel;

    @Override
    public void betesz(MutableLiveData<Lencse> lencseMutableLiveData) {
        Lencse value = lencseMutableLiveData.getValue();
        if(value != null && value.getBetetelIdopont() == 0) value.setBetetelIdopont(System.currentTimeMillis());
        else return;
        lencseMutableLiveData.postValue(value);
        Log.i(TAG, "betesz: lefutott");
    }

    @Override
    public void kivesz(MutableLiveData<Lencse> lencseMutableLiveData) {
        Lencse value = lencseMutableLiveData.getValue();
        if(value != null && value.getKivetelIdopont() == 0) value.setKivetelIdopont(System.currentTimeMillis());
        else return;
        lencseMutableLiveData.postValue(value);

        lencseViewModel.insert(value);

        Log.i(TAG, "kivesz: lefutott");
    }

    @Override
    public void clear(MutableLiveData<Lencse> lencseMutableLiveData) {
        Lencse value = lencseMutableLiveData.getValue();
        if(value != null && value.getKivetelIdopont() != 0) value.setKivetelIdopont(0);
        else return;
        lencseMutableLiveData.postValue(value);
        Log.i(TAG, "clear: lefutott");
    }
}
