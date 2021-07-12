package com.norbo.project.lencsenaplov2.db.dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.norbo.project.lencsenaplov2.db.entities.KezdoIdopontEntity;

@Dao
public interface KezdoIdopontDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(KezdoIdopontEntity kezdoIdopont);

    @Query("SELECT * FROM kezdoidopont_table LIMIT 1")
    LiveData<KezdoIdopontEntity> select();

    @Query("DELETE FROM kezdoidopont_table WHERE kezdoIdopont =:kezdoIdopont")
    void delete(long kezdoIdopont);

    @Query("SELECT * FROM kezdoidopont_table LIMIT 1")
    Cursor selectCursor();
}
