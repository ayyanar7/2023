package com.ag.bta.main.wavelayout.sample;

import android.app.Activity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


import com.ag.bta.utils.constant.FragmentConstants;
import com.ag.bta.main.wavelayout.utils.BaseUtils;
import com.ag.bta.main.wavelayout.utils.CardAdapter;
import com.ag.bta.main.wavelayout.utils.ModelConfiguration;
import com.ag.bta.main.wavelayout.view.WaveLayoutRecyclerView;
 

import com.ag.bta.main.R;

public class WaveLayoutActivity extends Activity   implements OnItemClickListener {
   
    public static final String EXTRA_TYPE = "type";

    private WaveLayoutRecyclerView WaveRecycler;
    private CardAdapter mAdapter;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int type = getType();

        RecyclerView.LayoutManager layoutManager;

        final ModelConfiguration modelConfiguration = BaseUtils.getModelConfiguration(type, this,R.string.nav_header_title);
        setTheme(modelConfiguration.getStyleResource());
        setContentView(modelConfiguration.getLayoutResource());
        layoutManager = modelConfiguration.getLayoutManager();
        setTitle(modelConfiguration.getTitleResource());

        WaveRecycler = findViewById(R.id.wavelayout_recycler_view);

        if (modelConfiguration.getItemDecoration() != null) {
            WaveRecycler.addItemDecoration(modelConfiguration.getItemDecoration());
        }

        mAdapter = new CardAdapter();
        mAdapter.setType(type);

        WaveRecycler.setLayoutManager(layoutManager);
        WaveRecycler.setAdapter(mAdapter);
        WaveRecycler.showWaveAdapter();

        WaveRecycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadCards();
            }
        }, 3000);
        
        WaveRecycler.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
			Toast.makeText(v.getContext(), "Wave recyler view clicked..", Toast.LENGTH_LONG).show();
			}
        	
        });
    }

    private void loadCards() {
        int type = getType();

        mAdapter.setCards(BaseUtils.getHomeCards(getResources()));
        WaveRecycler.hideWaveAdapter();
    }

    private int getType() {
        return getIntent().getIntExtra(EXTRA_TYPE, FragmentConstants.ITEM_TYPE_LIST_ONE);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}

	 
}
