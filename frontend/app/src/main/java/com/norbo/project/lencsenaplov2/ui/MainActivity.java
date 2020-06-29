package com.norbo.project.lencsenaplov2.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.KezdoIdopont;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.databinding.ActivityMainBinding;
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.di.controller.ControllerComponent;
import com.norbo.project.lencsenaplov2.di.controller.ControllerModule;
import com.norbo.project.lencsenaplov2.ui.rcviews.LencseAdapterFactory;
import com.norbo.project.lencsenaplov2.ui.utils.DataUtils;
import com.norbo.project.lencsenaplov2.ui.utils.LencseAdatToltoController;
import com.norbo.project.lencsenaplov2.ui.utils.UpdateLencseUI;
import com.norbo.project.lencsenaplov2.ui.utils.actions.MainAction;

import java.util.Collections;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements UpdateLencseUI {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int FILE_CHOOSER_CODE = 100;

    private MutableLiveData<Lencse> lencseMutableLiveData;
    private MutableLiveData<Long> currentTime;
    private KezdoIdopont mainKezdoIdopont;

    @Inject
    LencseViewModel viewModel;

    @Inject
    LencseAdapterFactory lencseAdapterFactory;

    @Inject
    LencseAdatToltoController adatTolto;

    @Inject
    DataUtils dataUtils;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getControllerComponent().inject(this);
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
        binding.setDataUtils(dataUtils);
        binding.setLencseadat(lencseMutableLiveData);
        binding.setAction(new MainAction(this));
        binding.setElapsedtime(currentTime);

        viewModel.getLencseData().observe(this, lencseList -> {
            if(lencseList.size() != 0) {
                Collections.sort(lencseList,
                        ((o1, o2) -> Long.compare(o2.getBetetelIdopont(), o1.getBetetelIdopont())));
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
        } else if(item.getItemId() == R.id.menu_add_list) {
            loadLencseAdat();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == FILE_CHOOSER_CODE && resultCode == RESULT_OK) {
            adatTolto.loadLencseAdat(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadLencseAdat() {
        Intent fileChooser = new Intent(Intent.ACTION_GET_CONTENT);
        fileChooser.setType("*/*");
        fileChooser.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"text/plain"});
        fileChooser.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(fileChooser, FILE_CHOOSER_CODE);
    }
}