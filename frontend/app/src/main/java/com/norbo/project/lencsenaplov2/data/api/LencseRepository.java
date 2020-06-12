package com.norbo.project.lencsenaplov2.data.api;

import androidx.lifecycle.LiveData;

import com.norbo.project.lencsenaplov2.data.model.KezdoIdopont;
import com.norbo.project.lencsenaplov2.data.model.Lencse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LencseRepository {
    CompletableFuture<Long> insert(Lencse lencse);
    LiveData<List<Lencse>> selectAll();

    LiveData<KezdoIdopont> selectKezdoIdopont();
    CompletableFuture<Long> insert(KezdoIdopont kezdoIdopont);

    void deleteKezdoIdopont(long betetelIdopont);
}