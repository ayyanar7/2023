package com.ag.bta.utils.lazyloading;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


public class LazyLoadViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> liveData = new MutableLiveData<>();

    private Handler handler = new Handler();

    public LazyLoadViewModel(@NonNull Application application) {
        super(application);
    }

    public void addFragmentDelayed(long delay) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                liveData.setValue(true);
            }
        }, delay);
    }
}
