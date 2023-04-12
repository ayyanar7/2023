package com.ag.bta.main.fragments.navdrawer.products;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> name;

    public void setName(String name) {
        this.name.setValue(name);
    }

    public MutableLiveData<String> getName(){
        if(name == null){
            name = new MutableLiveData<>();
        }
        return name;
    }
}
