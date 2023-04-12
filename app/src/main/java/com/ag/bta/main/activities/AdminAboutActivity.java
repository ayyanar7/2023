package com.ag.bta.main.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ag.bta.login.LoginActivity;
import com.ag.bta.main.R;

import com.ag.bta.main.fragments.navdrawer.about.helper.AdminAboutHelper;

public class AdminAboutActivity extends FragmentActivity {

   @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setTheme(AdminAboutHelper.theme);
        setContentView(new LinearLayout(this));

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new FragmentSample())
                .commit();
    }
        @Override
        public void onBackPressed() {
        // TODO Auto-generated method stub
        //super.onBackPressed();

        Intent it = new Intent( this, LoginActivity.class);
        startActivity(it);

    }
        @Override
        protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        finishAffinity();
    }


        public static class FragmentSample extends Fragment {



            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                return inflater.inflate(R.layout.about_sample_view, container, false);
            }

            @Override
            public void onActivityCreated(@Nullable Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);
                setRetainInstance(true);

                AdminAboutHelper.with(getActivity()).init().loadAbout();
            }
        }

    }

