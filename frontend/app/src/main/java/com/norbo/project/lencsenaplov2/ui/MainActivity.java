package com.norbo.project.lencsenaplov2.ui;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;

import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.data.repositories.LocalDatabaseLencseRepository;
import com.norbo.project.lencsenaplov2.databinding.ActivityMainBinding;
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    private Lencse ujLencse;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((LencsenaploApplication)getApplicationContext()).getGraph().inject(this);
        super.onCreate(savedInstanceState);

        ujLencse = new Lencse();

        if(savedInstanceState != null && savedInstanceState.containsKey(LENCSE_SAVED_KEY)) {
            ujLencse = (Lencse) savedInstanceState.getSerializable(LENCSE_SAVED_KEY);
        }

        binding.setLencseadat(ujLencse);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putSerializable(LENCSE_SAVED_KEY, ujLencse);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public class Action {
        public void betesz() {
            binding.getLencseadat().setBetetelIdopont(System.currentTimeMillis());
        }

        public void kivesz() {
            binding.getLencseadat().setKivetelIdopont(System.currentTimeMillis());
        }
    }
}