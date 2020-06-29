package com.norbo.project.lencsenaplov2.di;

import android.app.Application;

import androidx.room.Room;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.data.repositories.LocalDatabaseLencseRepository;
import com.norbo.project.lencsenaplov2.db.LencseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {
    private Application application;

    public DataBaseModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    LencseDatabase lencseDatabase(Application application) {
        return Room.databaseBuilder(application, LencseDatabase.class, LencseDatabase.DB_NAME)
                .addMigrations(LencseDatabase.MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    LencseRepository lencseRepository(LencseDatabase database) {
        return new LocalDatabaseLencseRepository(database);
    }
}
