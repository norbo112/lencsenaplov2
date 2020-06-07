package com.norbo.project.lencsenaplov2.data.repositories;

import androidx.lifecycle.LiveData;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.db.LencseDatabase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

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
    public Future<Long> insert(final Lencse lencse) {
        return LencseDatabase.executor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return lencseDatabase.lencseDao().insert(lencse);
            }
        });
    }

    @Override
    public LiveData<List<Lencse>> selectAll() {
        return lencseDatabase.lencseDao().selectAll();
    }
}
