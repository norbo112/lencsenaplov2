package com.norbo.project.lencsenaplov2.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.KezdoIdopont;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.databinding.ActivityMainBinding;
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.ui.rcviews.LencseAdapterFactory;
import com.norbo.project.lencsenaplov2.ui.utilts.UpdateLencseUI;
import com.norbo.project.lencsenaplov2.ui.utilts.actions.MainAction;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements UpdateLencseUI {
    private static final String TAG = MainActivity.class.getSimpleName();

    private MutableLiveData<Lencse> lencseMutableLiveData;
    private MutableLiveData<Long> currentTime;
    private KezdoIdopont mainKezdoIdopont;

    @Inject
    LencseViewModel viewModel;

    @Inject
    LencseAdapterFactory lencseAdapterFactory;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((LencsenaploApplication)getApplicationContext()).getGraph().inject(this);
        super.onCreate(savedInstanceState);

        setSupportActionBar(binding.toolbar);

        lencseMutableLiveData = new MutableLiveData<>();
        currentTime = new MutableLiveData<>();
        currentTime.setValue(System.currentTimeMillis());
        lencseMutableLiveData.setValue(new Lencse());

        if(savedInstanceState != null) {
            if(savedInstanceState.containsKey(LENCSE_SAVED_KEY)) {
                lencseMutableLiveData.setValue((Lencse) savedInstanceState.getSerializable(LENCSE_SAVED_KEY));
            }
        }

        binding.setLifecycleOwner(this);
        binding.setLencseadat(lencseMutableLiveData);
        binding.setAction(new MainAction(this));
        binding.setElapsedtime(currentTime);

        viewModel.getLencseData().observe(this, lencseList -> {
            if(lencseList.size() != 0) {
                binding.lencsercviewTitle.setText(lencseList.size()+" lencseadat rögzítve");
                binding.lencsercview.setAdapter(lencseAdapterFactory.create(MainActivity.this, lencseList));
                binding.lencsercview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.lencsercview.setItemAnimator(new DefaultItemAnimator());
            } else {
                binding.lencsercviewTitle.setText("Nincsenek adatok rögzítve");
            }
        });

        viewModel.getKezdoIdopont().observe(this, kezdoIdopont -> {
            if(kezdoIdopont != null) {
                mainKezdoIdopont = kezdoIdopont;
                Lencse value = lencseMutableLiveData.getValue();
                value.setBetetelIdopont(kezdoIdopont.getKezdoIdopont());
                lencseMutableLiveData.postValue(value);
                currentTime.postValue(System.currentTimeMillis());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mainKezdoIdopont != null) currentTime.postValue(System.currentTimeMillis());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putSerializable(LENCSE_SAVED_KEY, lencseMutableLiveData.getValue());
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG, "onSaveInstanceState: lefutott");
    }

    @Override
    public void clearLencseUi() {
        lencseMutableLiveData.postValue(new Lencse());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_diagram) {
            startActivity(new Intent(this, ReportActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}