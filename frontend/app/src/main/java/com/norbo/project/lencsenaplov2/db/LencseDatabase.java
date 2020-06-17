package com.norbo.project.lencsenaplov2.db;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.norbo.project.lencsenaplov2.db.dao.KezdoIdopontDao;
import com.norbo.project.lencsenaplov2.db.dao.LencseDao;
import com.norbo.project.lencsenaplov2.db.entities.KezdoIdopontEntity;
import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

@Singleton
@Database(entities = {LencseEntity.class, KezdoIdopontEntity.class}, version = 2, exportSchema = false)
public abstract class LencseDatabase extends RoomDatabase {
    public static final String DB_NAME = "lencsenaplo.db";
    public abstract LencseDao lencseDao();
    public abstract KezdoIdopontDao kezdoIdopontDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService executor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE lencse_table ADD COLUMN tisztitoViz INTEGER NOT NULL DEFAULT 0");
        }
    };
}
