package com.norbo.project.lencsenaplov2.data.api;

import androidx.lifecycle.LiveData;

import com.norbo.project.lencsenaplov2.db.entities.KezdoIdopontEntity;
import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LencseRepository {
    CompletableFuture<Long> insert(LencseEntity lencse);
    LiveData<List<LencseEntity>> selectAll();

    LiveData<KezdoIdopontEntity> selectKezdoIdopont();
    CompletableFuture<Long> insert(KezdoIdopontEntity kezdoIdopont);

    void deleteKezdoIdopont(long betetelIdopont);

    void insertAll(List<LencseEntity> lencseList);
}