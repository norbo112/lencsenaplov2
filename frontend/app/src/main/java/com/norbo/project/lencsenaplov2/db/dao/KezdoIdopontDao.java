package com.norbo.project.lencsenaplov2.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.norbo.project.lencsenaplov2.data.model.KezdoIdopont;
import com.norbo.project.lencsenaplov2.data.model.Lencse;

import java.util.List;

@Dao
public interface KezdoIdopontDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(KezdoIdopont kezdoIdopont);

    @Query("SELECT * FROM kezdoidopont_table LIMIT 1")
    LiveData<KezdoIdopont> select();

    @Query("DELETE FROM kezdoidopont_table WHERE kezdoIdopont =:kezdoIdopont")
    void delete(long kezdoIdopont);
}
