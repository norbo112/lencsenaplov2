package com.norbo.project.lencsenaplov2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.norbo.project.lencsenaplov2.db.dao.KezdoIdopontDao;
import com.norbo.project.lencsenaplov2.db.dao.LencseDao;
import com.norbo.project.lencsenaplov2.db.entities.KezdoIdopontEntity;
import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

@Singleton
@Database(entities = {LencseEntity.class, KezdoIdopontEntity.class}, version = 1, exportSchema = false)
public abstract class LencseDatabase extends RoomDatabase {
    public static final String DB_NAME = "lencsenaplo.db";
    public abstract LencseDao lencseDao();
    public abstract KezdoIdopontDao kezdoIdopontDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService executor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
}
