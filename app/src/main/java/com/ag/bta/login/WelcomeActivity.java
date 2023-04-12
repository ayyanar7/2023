package com.ag.bta.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.ag.bta.main.R;
import com.ag.bta.main.activities.HomeActivity;
import com.ag.bta.utils.database.sqlite.DesignTable;


import java.util.Timer;
import java.util.TimerTask;


public class WelcomeActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    Timer timer;
    int page_position = 0;

    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DesignTable design = new DesignTable(this);
        boolean isAvailable = design.checkTable();

       // localStorage = new LocalStorage(getApplicationContext());
        if (isAvailable) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }else {

            setContentView(R.layout.activity_welcome);

            timer = new Timer();
            viewPager = findViewById(R.id.viewPager);

            sliderDotspanel = findViewById(R.id.SliderDots);

            WelcomePagerAdapter viewPagerAdapter = new WelcomePagerAdapter(this);

            viewPager.setAdapter(viewPagerAdapter);

            dotscount = viewPagerAdapter.getCount();
            dots = new ImageView[dotscount];

            for (int i = 0; i < dotscount; i++) {

                dots[i] = new ImageView(this);
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.welcome_non_active_dot));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params.setMargins(8, 0, 8, 0);

                sliderDotspanel.addView(dots[i], params);

            }

            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.welcome_active_dot));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    for (int i = 0; i < dotscount; i++) {
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.welcome_non_active_dot));
                    }

                    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.welcome_active_dot));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            scheduleSlider();
        }
    }

    public void scheduleSlider() {

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == dotscount) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                viewPager.setCurrentItem(page_position, true);
            }
        };

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 4000);
    }

    @Override
    protected void onStop() {
        timer.cancel();
        super.onStop();
    }

    @Override
    protected void onPause() {
        timer.cancel();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        timer = null;
        super.onDestroy();
    }

    public void onLetsClicked(View view) {
        startActivity(new Intent(getApplicationContext(), SplashsActivity.class));
        finish();
       overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        //overridePendingTransition(R.anim.splash_animation, R.anim.splash_animation_fadeout);
    }
}