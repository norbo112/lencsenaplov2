package com.norbo.project.lencsenaplov2.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.data.model.Lencse;

import java.util.List;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LencseViewModel extends ViewModel {
    private LencseRepository repository;
    private MutableLiveData<List<Lencse>> lencseData;

    @Inject
    public LencseViewModel(LencseRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Lencse>> getLencseData() {
        lencseData.setValue(repository.selectAll());
        return lencseData;
    }

    public Future<Long> insert(Lencse lencse) {
        return repository.insert(lencse);
    }
}
