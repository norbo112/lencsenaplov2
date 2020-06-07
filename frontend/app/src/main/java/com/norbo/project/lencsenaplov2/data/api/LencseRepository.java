package com.norbo.project.lencsenaplov2.data.api;

import androidx.lifecycle.LiveData;

import com.norbo.project.lencsenaplov2.data.model.Lencse;

import java.util.List;
import java.util.concurrent.Future;

public interface LencseRepository {
    Future<Long> insert(Lencse lencse);
    LiveData<List<Lencse>> selectAll();
}