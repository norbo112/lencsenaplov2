package com.norbo.project.lencsenaplov2.data.api;

import com.norbo.project.lencsenaplov2.data.model.Lencse;

import java.util.List;
import java.util.concurrent.Future;

public interface LencseRepository {
    Future<Long> insert(Lencse lencse);
    List<Lencse> selectAll();
}