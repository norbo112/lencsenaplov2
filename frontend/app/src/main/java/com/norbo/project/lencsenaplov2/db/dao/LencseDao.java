package com.norbo.project.lencsenaplov2.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;

import java.util.List;

@Dao
public interface LencseDao {
    @Insert
    long insert(LencseEntity lencse);

    @Query("SELECT * FROM lencse_table ORDER BY betetelIdopont DESC")
    LiveData<List<LencseEntity>> selectAll();
}
