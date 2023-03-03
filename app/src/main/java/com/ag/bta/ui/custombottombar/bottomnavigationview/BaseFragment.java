package com.ag.bta.ui.custombottombar.bottomnavigationview;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ag.bta.main.R;



public class BaseFragment extends Fragment {
    private String title;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get title
     title = getArguments().getString("title");
    }
TextView tvtitle = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.bottombarview_base_fragment, null);
        // bind view
       // binding = DataBindingUtil.bind(view);
        tvtitle = (TextView)view.findViewById(R.id.tv_title);
        tvtitle.setText(title);
        return view;
    }
}
