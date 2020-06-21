package com.norbo.project.lencsenaplov2.di;

import android.content.Context;

import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.data.repositories.LocalDatabaseLencseRepository;
import com.norbo.project.lencsenaplov2.ui.utilts.DataUtils;
import com.norbo.project.lencsenaplov2.ui.utilts.LencseAdatToltoController;
import com.norbo.project.lencsenaplov2.db.LencseDatabase;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.utilts.ConvertEntities;
import com.norbo.project.lencsenaplov2.ui.utilts.MyToaster;

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
                .addMigrations(LencseDatabase.MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    LencseAdatToltoController provideAdatTolto(Context context, LencseViewModel viewModel, MyToaster toaster) {
        return new LencseAdatToltoController(context, viewModel, toaster);
    }

    @Singleton
    @Provides
    ConvertEntities provideConvertEntities() {
        return new ConvertEntities();
    }

    @Singleton
    @Provides
    MyToaster provideMyToaster(Context context) {
        return new MyToaster(context);
    }

    @Singleton
    @Provides
    DataUtils provideDataUtils() {
        return new DataUtils();
    }

    @Singleton
    @Provides
    Context getContext() {
        return context;
    }
}
