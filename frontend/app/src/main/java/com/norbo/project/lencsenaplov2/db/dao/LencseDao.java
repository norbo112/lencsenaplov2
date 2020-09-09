package com.norbo.project.lencsenaplov2.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;

import java.util.List;

@Dao
public interface LencseDao {
    @Insert
    long insert(LencseEntity lencse);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<LencseEntity> entities);

    @Query("SELECT * FROM lencse_table")
    LiveData<List<LencseEntity>> selectAll();

    @Query("SeLECT * FROM lencse_table")
    List<LencseEntity> selectEntities();

    @Query("SELECT count(*) FROM lencse_table")
    int countRows();

    @Query("SELECT * FROM lencse_table WHERE tisztitoViz = 1")
    LiveData<List<LencseEntity>> selectTisztitoViz();
}
