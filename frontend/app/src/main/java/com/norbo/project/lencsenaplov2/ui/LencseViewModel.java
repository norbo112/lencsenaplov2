package com.norbo.project.lencsenaplov2.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.data.model.KezdoIdopont;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.db.entities.KezdoIdopontEntity;
import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;
import com.norbo.project.lencsenaplov2.ui.utils.ConvertEntities;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

public class LencseViewModel extends ViewModel {
    private LencseRepository repository;
    private LiveData<List<Lencse>> lencseData;
    private LiveData<List<Lencse>> lencseTisztitoViz;
    private ConvertEntities converter;

    public LencseViewModel(LencseRepository repository, ConvertEntities converter) {
        this.repository = repository;
        this.converter = converter;
        this.lencseData = Transformations.map(
                repository.selectAll(), entities -> entities.stream().map(converter::convertFromEntityLencse)
                .collect(Collectors.toList()));
        this.lencseTisztitoViz = Transformations.map(repository.getLencseTisztitoViz(), entities ->
            entities.stream().map(converter::convertFromEntityLencse).collect(Collectors.toList()));
    }

    public LiveData<KezdoIdopont> getKezdoIdopont() {
        return Transformations.map(repository.selectKezdoIdopont(),
                (entity) -> converter.convertFromEntityKezdo(entity));
    }

    public LiveData<List<Lencse>> getLencseData() {
        return lencseData;
    }

    public CompletableFuture<Long> insert(Lencse lencse) {
        return repository.insert(new LencseEntity(lencse.getBetetelIdopont(), lencse.getKivetelIdopont(), lencse.getTisztitoViz()));
    }

    public void insertAll(List<Lencse> lencseList) {
        List<LencseEntity> lencseEntities =
                lencseList.stream()
                        .map((lencse -> converter.convertFromUILencse(lencse)))
                        .collect(Collectors.toList());
        repository.insertAll(lencseEntities);
    }

    public CompletableFuture<Long> insertKezdoIdopont(KezdoIdopont kezdoIdopont) {
        return repository.insert(new KezdoIdopontEntity(kezdoIdopont.getKezdoIdopont()));
    }

    public LiveData<List<Lencse>> getLencseTisztitoViz() {
        return lencseTisztitoViz;
    }

    public void deleteKezdoIdopont(long betetelIdopont) {
        repository.deleteKezdoIdopont(betetelIdopont);
    }
}
