package com.norbo.project.lencsenaplov2.di;

import android.content.Context;

import androidx.room.Room;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.data.repositories.LocalDatabaseLencseRepository;
import com.norbo.project.lencsenaplov2.db.LencseDatabase;
import com.norbo.project.lencsenaplov2.db.dao.LencseDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirstModule {
    public final Context context;

    public FirstModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    LencseRepository provideLencseRepository(LencseDatabase database) {
        return new LocalDatabaseLencseRepository(database);
    }

    @Singleton
    @Provides
    LencseDatabase provideLencseDatabase(Context context) {
        return Room.databaseBuilder(context, LencseDatabase.class, LencseDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    Context getContext() {
        return context;
    }
}
