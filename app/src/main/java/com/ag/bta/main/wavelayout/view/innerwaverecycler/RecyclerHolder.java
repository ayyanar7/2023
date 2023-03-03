package com.ag.bta.main.wavelayout.view.innerwaverecycler;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.ag.bta.constants.FragmentConstants;
import com.ag.bta.main.R;
import com.ag.bta.main.wavelayout.models.ItemCard;
import com.ag.bta.main.wavelayout.utils.CardAdapter;
import com.ag.bta.main.wavelayout.utils.ModelConfiguration;
import com.ag.bta.main.wavelayout.view.WaveLayoutRecyclerView;

import java.util.ArrayList;


public class RecyclerHolder extends ViewHolder  {
	private WaveLayoutRecyclerView waveLayoutRecycler;
	private WaveLayoutRecyclerView innerRecycler;
	private CardAdapter irAdapter;
	private ModelConfiguration innerConfiguration;
	public static RecyclerHolder newInstance(ViewGroup container,ModelConfiguration innerRconfig, int itemtype) {
		View root = LayoutInflater.from(container.getContext()).inflate(R.layout.wavelayout_outer_recyclerview, container, false);

		return new RecyclerHolder(root,innerRconfig, itemtype);
	}

	private RecyclerHolder(View itemView, ModelConfiguration innerRconfig, int itemtype) {
		super(itemView);

		intializeHolder( itemView,  innerRconfig, itemtype);

		/*mTitleView.setOnClickListener(this);
		mDescView.setOnClickListener(this);
		mSummaryView.setOnClickListener(this);
		mThumbnailView.setOnClickListener(this);
		getAd
*/
	}


	private void intializeHolder(View itemView, ModelConfiguration innerRconfig,int itemtype){
		innerConfiguration = innerRconfig;
		innerRecycler = itemView.findViewById(R.id.wavelayout_inner_recycler);

	}

	public void bind(final WaveLayoutRecyclerView argRecycler,   final int rcyclerslno) {
		innerRecycler =  argRecycler;



		if (demoConfiguration.getItemDecoration() != null) {
			innerRecycler.addItemDecoration(demoConfiguration.getItemDecoration());
		}

		irAdapter = new CardAdapter();
		irAdapter.setType(innerrType);
		RecyclerView.LayoutManager layoutManager = demoConfiguration.getLayoutManager();
		innerRecycler.setLayoutManager(layoutManager);
		innerRecycler.setAdapter(irAdapter);
		innerRecycler.showWaveAdapter();

		innerRecycler.postDelayed(new Runnable() {
			@Override
			public void run() {
				loadCards();
			}
		}, FragmentConstants.LOADING_DELAY_TIME);

		innerRecycler.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				Toast.makeText(v.getContext(), "Wave recyler view clicked..", Toast.LENGTH_LONG).show();
			}

		});

	}
	ArrayList<ItemCard> irlist;
	private void loadCards() {
		irlist = new ArrayList<ItemCard>();

		irlist.add(new ItemCard("Title1 ","",0));
		irlist.add(new ItemCard("Title2 ","",0));
		 irlist.add(new ItemCard("Title3","",0));
		irAdapter.setCards(irlist);
		innerRecycler.hideWaveAdapter();
	}

}

	


