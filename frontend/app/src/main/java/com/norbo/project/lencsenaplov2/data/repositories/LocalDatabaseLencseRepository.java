package com.norbo.project.lencsenaplov2.data.repositories;

import androidx.lifecycle.LiveData;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.db.LencseDatabase;
import com.norbo.project.lencsenaplov2.db.entities.KezdoIdopontEntity;
import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocalDatabaseLencseRepository implements LencseRepository {
    private LencseDatabase lencseDatabase;

    @Inject
    public LocalDatabaseLencseRepository(LencseDatabase lencseDatabase) {
        this.lencseDatabase = lencseDatabase;
    }

    @Override
    public CompletableFuture<Long> insert(final LencseEntity lencse) {
        return CompletableFuture.supplyAsync(() -> {
            return lencseDatabase.lencseDao().insert(lencse);
        });
    }

    @Override
    public CompletableFuture<Long> insert(final KezdoIdopontEntity kezdoIdopont) {
        return CompletableFuture.supplyAsync(()-> {
            return lencseDatabase.kezdoIdopontDao().insert(kezdoIdopont);
        });
    }

    @Override
    public LiveData<List<LencseEntity>> selectAll() {
        return lencseDatabase.lencseDao().selectAll();
    }

    @Override
    public LiveData<KezdoIdopontEntity> selectKezdoIdopont() {
        return lencseDatabase.kezdoIdopontDao().select();
    }

    @Override
    public void deleteKezdoIdopont(final long betetelIdopont) {
        LencseDatabase.executor.execute(new Runnable() {
            @Override
            public void run() {
                lencseDatabase.kezdoIdopontDao().delete(betetelIdopont);
            }
        });
    }
}
