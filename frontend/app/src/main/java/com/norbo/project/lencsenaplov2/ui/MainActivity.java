package com.norbo.project.lencsenaplov2.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.norbo.project.lencsenaplov2.R;
import com.norbo.project.lencsenaplov2.data.model.Lencse;
import com.norbo.project.lencsenaplov2.data.repositories.LocalDatabaseLencseRepository;
import com.norbo.project.lencsenaplov2.databinding.ActivityMainBinding;
import com.norbo.project.lencsenaplov2.di.LencsenaploApplication;
import com.norbo.project.lencsenaplov2.ui.rcviews.LencseAdapterFactory;
import com.norbo.project.lencsenaplov2.ui.utilts.ClearLencse;
import com.norbo.project.lencsenaplov2.ui.utilts.actions.MainAction;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements ClearLencse {
    private static final String TAG = MainActivity.class.getSimpleName();

    private MutableLiveData<Lencse> lencseMutableLiveData;
    private MutableLiveData<Long> currentTime;

    @Inject
    LencseViewModel viewModel;

    @Inject
    LencseAdapterFactory lencseAdapterFactory;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((LencsenaploApplication)getApplicationContext()).getGraph().inject(this);
        super.onCreate(savedInstanceState);

        lencseMutableLiveData = new MutableLiveData<>();
        currentTime = new MutableLiveData<>();
        currentTime.setValue(System.currentTimeMillis());
        lencseMutableLiveData.setValue(new Lencse());

        if(savedInstanceState != null && savedInstanceState.containsKey(LENCSE_SAVED_KEY)) {
            lencseMutableLiveData.setValue((Lencse) savedInstanceState.getSerializable(LENCSE_SAVED_KEY));
        }

        binding.setLifecycleOwner(this);
        binding.setLencseadat(lencseMutableLiveData);
        binding.setAction(new MainAction(this));
        binding.setElapsedtime(currentTime);

        elapsedTimeThread.start();

        viewModel.getLencseData().observe(this, new Observer<List<Lencse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(List<Lencse> lencseList) {
                if(lencseList.size() != 0) {
                    binding.lencsercviewTitle.setText(lencseList.size()+" lencseadat rögzítve");
                    binding.lencsercview.setAdapter(lencseAdapterFactory.create(MainActivity.this, lencseList));
                    binding.lencsercview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    binding.lencsercview.setItemAnimator(new DefaultItemAnimator());
                } else {
                    binding.lencsercviewTitle.setText("Nincsenek adatok rögzítve");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(elapsedTimeThread.isInterrupted()) elapsedTimeThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        elapsedTimeThread.interrupt();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putSerializable(LENCSE_SAVED_KEY, lencseMutableLiveData.getValue());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void clearLencseUi() {
        lencseMutableLiveData.postValue(new Lencse());
    }

    private Thread elapsedTimeThread = new Thread(){
        private final AtomicBoolean futas = new AtomicBoolean(true);
        @Override
        public void run() {
            while(futas.get()) {
                currentTime.postValue(System.currentTimeMillis());
                try {
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public synchronized void start() {
            super.start();
            futas.set(true);
        }

        @Override
        public void interrupt() {
            super.interrupt();
            futas.set(false);
        }
    };
}