package com.norbo.project.lencsenaplov2.ui.utilts.actions;

import androidx.lifecycle.MutableLiveData;

public interface Actioner<T> {
    void betesz(MutableLiveData<T> liveData);
    void kivesz(MutableLiveData<T> liveData);
    void clear(MutableLiveData<T> liveData);
}
