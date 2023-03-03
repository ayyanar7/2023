package com.ag.bta.main.wavelayout.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ag.bta.main.wavelayout.utils.BaseUtils;

import com.ag.bta.main.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wavelayout_activity_main);

        final Button firstListDemoButton = findViewById(R.id.list_demo_button);
        final Button firstGridDemoButton = findViewById(R.id.grid_demo_button);

        //createClickListener(firstListDemoButton, Frag.TYPE_LIST);
        //createClickListener(firstGridDemoButton, BaseUtils.TYPE_GRID);

        final Button secondListDemoButton = findViewById(R.id.list_second_demo_button);
        final Button secondGridDemoButton = findViewById(R.id.grid_second_demo_button);

       // createClickListener(secondListDemoButton, BaseUtils.TYPE_SECOND_LIST);
       // createClickListener(secondGridDemoButton, BaseUtils.TYPE_SECOND_GRID);
    }

    private void createClickListener(Button button, final int demoType) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDemo(demoType);
            }
        });
    }

    private void startDemo(int demoType) {
        Intent intent = new Intent(this, WaveLayoutActivity.class);
        intent.putExtra(WaveLayoutActivity.EXTRA_TYPE, demoType);
        startActivity(intent);
    }
}
