package com.norbo.project.lencsenaplov2.data.api;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.db.entities.KezdoIdopontEntity;
import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LencseRepository {
    CompletableFuture<Long> insert(LencseEntity lencse);
    LiveData<List<LencseEntity>> selectAll();

    LiveData<KezdoIdopontEntity> selectKezdoIdopont();
    Cursor selectKezdoIdopontCursor();
    CompletableFuture<Long> insert(KezdoIdopontEntity kezdoIdopont);

    LiveData<List<LencseEntity>> getLencseTisztitoViz();

    void deleteKezdoIdopont(long betetelIdopont);

    void insertAll(List<LencseEntity> lencseList);
}