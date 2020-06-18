package com.norbo.project.lencsenaplov2.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.db.dao.LencseDao;
import com.norbo.project.lencsenaplov2.db.entities.LencseEntity;
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OldLencseAdatTolto {
    private static final String TAG = "OldLencseAdatTolto";
    private Context context;

    @Inject
    LencseViewModel viewModel;

    public OldLencseAdatTolto(Context context) {
        ((LencsenaploApplication)context.getApplicationContext()).getGraph().inject(this);
    }

    public void insertOldData() {
        viewModel.insertAll(getLencseEntitiesFromRaw());
    }

    private List<Lencse> getLencseEntitiesFromRaw() {
        List<Lencse> lencseEntities = new ArrayList<>();
        try(BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    context.getResources().openRawResource(R.raw.old_lencse_adat)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] sp = line.split("\t");
                lencseEntities.add(new Lencse(
                        Long.parseLong(sp[0]),
                        Long.parseLong(sp[1]), Integer.parseInt(sp[2])));
            }
        } catch (IOException e) {
            Log.e(TAG, "getLencseEntitiesFromRaw: ", e);
        }

        return lencseEntities;
    }
}
