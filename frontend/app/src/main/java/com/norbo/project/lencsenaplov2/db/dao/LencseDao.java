package com.norbo.project.lencsenaplov2.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.norbo.project.lencsenaplov2.data.model.Lencse;

import java.util.List;

@Dao
public interface LencseDao {
    @Insert
    long insert(Lencse lencse);

    @Query("SELECT * FROM lencse_table")
    LiveData<List<Lencse>> selectAll();
}
