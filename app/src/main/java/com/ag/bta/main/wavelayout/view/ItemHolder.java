package com.ag.bta.main.wavelayout.view;

import android.graphics.drawable.Drawable;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.Objects.utils.Item;


import com.ag.bta.constants.FragmentConstants;
import com.ag.bta.main.R;

import com.ag.bta.main.wavelayout.models.ItemCard;


public class ItemHolder extends ViewHolder  implements View.OnClickListener {

	private TextView mTitleView;
	private TextView mDescView;
	private ImageView mThumbnailView;
	private TextView mSummaryView;

	public static ItemHolder newInstance(ViewGroup container,  int itemtype) {
		View root = LayoutInflater.from(container.getContext()).inflate(getItemResourceId(itemtype), container, false);

		return new ItemHolder(root, itemtype);
	}

	private ItemHolder(View itemView, int itemtype) {
		super(itemView);
		intializeHolder( itemView,   itemtype);

		/*mTitleView.setOnClickListener(this);
		mDescView.setOnClickListener(this);
		mSummaryView.setOnClickListener(this);
		mThumbnailView.setOnClickListener(this);
		getAd
*/
	}

	private TextView sno;
	private TextView card_numerictext;
	private TextView card_numerictext2;
	private TextView card_actiontext;
	private ImageView card_options_image;
	private ImageView card_options_image2;
	private void intializeHolder(View itemView, int itemtype){
		switch (itemtype) {
			case FragmentConstants.ITEM_TYPE_LIST_ONE:
				mTitleView = itemView.findViewById(R.id.card_title);
				mDescView = itemView.findViewById(R.id.card_subtitle);
				mSummaryView = itemView.findViewById(R.id.card_summary);
				mThumbnailView = itemView.findViewById(R.id.card_image);
				 	break;
			case FragmentConstants.ITEM_TYPE_LIST_TWO:
				sno = itemView.findViewById(R.id.Sno);
				mTitleView = itemView.findViewById(R.id.card_title);
				mDescView = itemView.findViewById(R.id.card_subtitle);
				mThumbnailView = itemView.findViewById(R.id.card_image);
				card_numerictext = itemView.findViewById(R.id.card_numerictext);
				card_numerictext2 = itemView.findViewById(R.id.card_numerictext2);
				card_actiontext = itemView.findViewById(R.id.card_actiontext);
				card_options_image = itemView.findViewById(R.id.card_options_image);
				card_options_image2 = itemView.findViewById(R.id.card_options_image2);

				break;

			case FragmentConstants.ITEM_TYPE_LIST_THREE:
				mTitleView = itemView.findViewById(R.id.card_title);
				mDescView = itemView.findViewById(R.id.card_subtitle);
				mSummaryView = itemView.findViewById(R.id.card_summary);
				mThumbnailView = itemView.findViewById(R.id.card_image);
				break;

			case FragmentConstants.ITEM_TYPE_GRID_ONE:
				mTitleView = itemView.findViewById(R.id.card_title);
				mDescView = itemView.findViewById(R.id.card_subtitle);
				mSummaryView = itemView.findViewById(R.id.card_summary);
				mThumbnailView = itemView.findViewById(R.id.card_image);
			 	break;

			case FragmentConstants.ITEM_TYPE_GRID_TWO:
				//sno = itemView.findViewById(R.id.Sno);
				mTitleView = itemView.findViewById(R.id.card_title);
				//mDescView = itemView.findViewById(R.id.card_subtitle);
				mThumbnailView = itemView.findViewById(R.id.card_image);
				card_numerictext = itemView.findViewById(R.id.card_numerictext);
				//card_numerictext2 = itemView.findViewById(R.id.card_numerictext2);
				card_actiontext = itemView.findViewById(R.id.card_actiontext);
				card_options_image = itemView.findViewById(R.id.card_options_image);
				card_options_image2 = itemView.findViewById(R.id.card_options_image2);

				break;
			default:

		}
		itemView.setOnClickListener(this);
	}

	public void bind(final ItemCard card, final int type, final int serialno) {


		switch(type){
			case FragmentConstants.ITEM_TYPE_LIST_ONE:
				mTitleView.setText(card.getTitle());
				mDescView.setText(card.getDescription());
				mSummaryView.setText(card.getSummaryText());
				//Glide.with(itemView.getContext()).load(card.getThumbnailUrl()).into(mThumbnailView);

				break;
			case FragmentConstants.ITEM_TYPE_LIST_TWO: //second news
			//	sno.setText(serialno);
				mDescView.setText(card.getDescription());
				mTitleView.setText(card.getTitle());
				card_numerictext.setText(card.getNumerictext());
				card_numerictext2.setText(card.getNumerictext2());
				card_actiontext.setText(card.getActiontext());
				//card_options_image.setImageResource();
				//card_options_image2.setImageResource();

				break;

			case FragmentConstants.ITEM_TYPE_LIST_THREE: //second news
				mTitleView.setText(card.getTitle());
				mDescView.setText(card.getDescription());
				mSummaryView.setText(card.getSummaryText());
				break;

			case FragmentConstants.ITEM_TYPE_GRID_ONE:
				mTitleView.setText(card.getTitle());
				mDescView.setText(card.getDescription());
				mSummaryView.setText(card.getSummaryText());
			 break;

			case FragmentConstants.ITEM_TYPE_GRID_TWO:
			 	mDescView.setText(card.getDescription());
				mTitleView.setText(card.getTitle());
				card_numerictext.setText(card.getNumerictext());

				card_actiontext.setText(card.getActiontext());
				 break;
			default:
				break;
		}
			}
	public void bind1(final ItemCard card ) {
		mTitleView.setText(card.getTitle());
		mDescView.setText(card.getDescription());
		mSummaryView.setText(card.getSummaryText());
      //Glide.with(itemView.getContext()).load(card.getThumbnailUrl()).into(mThumbnailView);
	}

//	public void bind(final ItemCard card, final int resId) {
//		mTitleView.setText(card.getTitle());
//		mDescView.setText(card.getDescription());
//		mSummaryView.setText(card.getSummaryText());
//		mThumbnailView.setImageResource(resId);
//     //Glide.with(itemView.getContext()).load(card.getThumbnailUrl()).into(mThumbnailView);
//	}

	public void bind(final ItemCard card, final Drawable drawable) {
		mTitleView.setText(card.getTitle());
		mDescView.setText(card.getDescription());
		mSummaryView.setText(card.getSummaryText());
		mThumbnailView.setImageDrawable(drawable);
 	}

	
	
	
	private static int getItemResourceId(int type) {
		int selectedLayoutResource;
		switch (type) {
			case FragmentConstants.ITEM_TYPE_LIST_ONE:
			selectedLayoutResource = R.layout.wavelayout_item_listtype_1;
			break;
			case FragmentConstants.ITEM_TYPE_LIST_TWO: //second news
			selectedLayoutResource = R.layout.wavelayout_item_listtype_3;
			break;

			case FragmentConstants.ITEM_TYPE_LIST_THREE: //second news
				selectedLayoutResource = R.layout.wavelayout_item_gridtype_2;
				break;

			case FragmentConstants.ITEM_TYPE_GRID_ONE:
				selectedLayoutResource = R.layout.wavelayout_item_gridtype_1;
				break;

		case FragmentConstants.ITEM_TYPE_GRID_TWO:
			selectedLayoutResource = R.layout.wavelayout_item_listtype_3;
			//selectedLayoutResource = R.layout.wave_layout_ecom_item;
			break;
		default:
			selectedLayoutResource = 0;
		}
		return selectedLayoutResource;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	int position = (int) v.getTag()	;

Toast.makeText(v.getContext(), "Selected position: "+position,  Toast.LENGTH_LONG).show();

// 		switch(ScreenNavUtils.getInstance().getScreen()) {
//		case ScreenNavUtils.SCREEN_HOME:
//			homeOnItemClick(v, position);
//			return;
//		case ScreenNavUtils.SCREEN_SALES:
//			salesOnItemClick(v, position);
//			return;
//		case ScreenNavUtils.SCREEN_SALES_TWO:
//			sales2OnItemClick(v, position);
//			return;
//
//		default:
//			sales2OnItemClick(v, position);
//			return;

		}		
	
	}

	


