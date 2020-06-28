package com.norbo.project.lencsenaplov2.di.controller;

import android.app.Activity;
import android.content.Context;

import androidx.room.Room;

import com.norbo.project.lencsenaplov2.data.api.LencseRepository;
import com.norbo.project.lencsenaplov2.data.repositories.LocalDatabaseLencseRepository;
import com.norbo.project.lencsenaplov2.db.LencseDatabase;
import com.norbo.project.lencsenaplov2.di.PerActivity;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;
import com.norbo.project.lencsenaplov2.ui.dialogs.LencseInfoDialog;
import com.norbo.project.lencsenaplov2.ui.rcviews.LencseAdapterFactory;
import com.norbo.project.lencsenaplov2.ui.utils.ConvertEntities;
import com.norbo.project.lencsenaplov2.ui.utils.DataUtils;
import com.norbo.project.lencsenaplov2.ui.utils.LencseAdatToltoController;
import com.norbo.project.lencsenaplov2.ui.utils.MyToaster;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {
    private Activity activity;

    public ControllerModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    public Activity getActivity() {
        return activity;
    }

    @PerActivity
    @Provides
    public Context getContext() { return activity; }

    @PerActivity
    @Provides
    LencseInfoDialog lencseInfoDialog(Activity activity, DataUtils dataUtils) {
        return new LencseInfoDialog(activity, dataUtils);
    }

    @PerActivity
    @Provides
    LencseRepository provideLencseRepository(LencseDatabase database) {
        return new LocalDatabaseLencseRepository(database);
    }

    @PerActivity
    @Provides
    LencseDatabase provideLencseDatabase(Context context) {
        return Room.databaseBuilder(context, LencseDatabase.class, LencseDatabase.DB_NAME)
                .addMigrations(LencseDatabase.MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build();
    }

    @PerActivity
    @Provides
    LencseAdapterFactory provideAdapterFactory() {
        return new LencseAdapterFactory();
    }

    @PerActivity
    @Provides
    LencseAdatToltoController provideAdatTolto(Context context, LencseViewModel viewModel, MyToaster toaster) {
        return new LencseAdatToltoController(context, viewModel, toaster);
    }

    @PerActivity
    @Provides
    LencseViewModel lencseViewModel(LencseRepository repository, ConvertEntities convertEntities) {
        return new LencseViewModel(repository, convertEntities);
    }

    @PerActivity
    @Provides
    ConvertEntities provideConvertEntities() {
        return new ConvertEntities();
    }

    @PerActivity
    @Provides
    MyToaster provideMyToaster(Context context) {
        return new MyToaster(context);
    }

    @PerActivity
    @Provides
    DataUtils provideDataUtils() {
        return new DataUtils();
    }
}
