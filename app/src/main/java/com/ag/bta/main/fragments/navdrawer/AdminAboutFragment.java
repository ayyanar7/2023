package com.ag.bta.main.fragments.navdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ag.bta.main.R;

import com.ag.bta.main.fragments.navdrawer.about.helper.AdminAboutHelper;

public class AdminAboutFragment extends Fragment{

   @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setTheme(AdminAboutHelper.theme);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_sample_view, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
setRetainInstance(true);
        AdminAboutHelper.with(getActivity()).init().loadAbout();
    }





    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }





    }

