package com.norbo.project.lencsenaplov2.ui.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.ui.LencseViewModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LencseAdatToltoController {
    private static final String TAG = "OldLencseAdatTolto";

    private Context context;
    private LencseViewModel viewModel;
    private MyToaster toaster;

    @Inject
    public LencseAdatToltoController(Context context, LencseViewModel viewModel, MyToaster toaster) {
        this.context = context;
        this.viewModel = viewModel;
        this.toaster = toaster;
    }

    public void loadLencseAdat(Uri contentUri) {
        List<Lencse> lencseList = getLencseEntitiesFromRaw(contentUri);
        viewModel.insertAll(lencseList);
        toaster.show("Adatok feltöltése befejeződott ["+lencseList.size()+"] db elemmel.");
    }

    private List<Lencse> getLencseEntitiesFromRaw(Uri contentUri) {
        List<Lencse> lencseEntities = new ArrayList<>();
        try(BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(Objects.requireNonNull(context.getContentResolver().openInputStream(contentUri))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] sp = line.split("\t");
                lencseEntities.add(new Lencse(
                        Long.parseLong(sp[0]),
                        Long.parseLong(sp[1]), Integer.parseInt(sp[2])));
            }
        } catch (NullPointerException e) {
            Log.e(TAG, "getLencseEntitiesFromRaw: Null a content resolver", e);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "getLencseEntitiesFromRaw: Nem található a fájl", e);
        } catch (IOException ex) {
            Log.e(TAG, "getLencseEntitiesFromRaw: Írási/Olvasási hiba", ex);
        }

        return lencseEntities;
    }
}
