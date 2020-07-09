package com.norbo.project.lencsenaplov2.di;

import android.app.Application;

import androidx.room.Room;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.data.repositories.LocalDatabaseLencseRepository;
import com.norbo.project.lencsenaplov2.db.LencseDatabase;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.utils.ConvertEntities;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    LencseDatabase lencseDatabase(Application application) {
        return Room.databaseBuilder(application, LencseDatabase.class, LencseDatabase.DB_NAME)
                .addMigrations(LencseDatabase.MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    LencseRepository lencseRepository(LencseDatabase database) {
        return new LocalDatabaseLencseRepository(database);
    }

    @Provides
    @Singleton
    LencseViewModel lencseViewModel(LencseRepository repository, ConvertEntities convertEntities) {
        return new LencseViewModel(repository, convertEntities);
    }

    @Provides
    @Singleton
    ConvertEntities provideConvertEntities() {
        return new ConvertEntities();
    }
}
