package com.ag.bta.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ag.bta.utils.constant.FragmentConstants;
import com.ag.bta.main.R;
import com.ag.bta.utils.Log;

import java.util.Objects;

public class ContainerFragment extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;


        // Required empty public constructor

        private  int mainTabposition = -1;
    private  int subTabposition = -1;
    private  int subSubTabPosition = -1;
    public ContainerFragment() {




    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SUB SUB TAB DESTROYED....."+this.mainTabposition+" "+this.subTabposition+" "+this.subSubTabPosition);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Bundle bundle =  this.getArguments();
        if(bundle != null){
            Log.d("Bundle is not null .SubTabMainFragment...");
            this.mainTabposition = bundle.getInt(FragmentConstants.KEY_MAIN_TAB_POS);
            this.subTabposition = bundle.getInt(FragmentConstants.KEY_SUB_SUB_TAB_POS);
            this.subSubTabPosition = bundle.getInt(FragmentConstants.KEY_SUB_SUB_TAB_POS);

        }else{
            Log.d("Bundle is null .SubTabMainFragment...");
        }
        Log.d("Inside create View .."+subSubTabPosition);
        view = inflater.inflate(R.layout.fragment_type3_apps, container, false);
        configureMainRecyclerView();

        return view;
    }

    private void configureMainRecyclerView() {
        mRecyclerView = view.findViewById(R.id.rv_top_app_list);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new
                DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(requireActivity().getBaseContext(),
                R.drawable.line_divider)));
        mRecyclerView.addItemDecoration(divider);

        loadAdapterData();
    }

    private void loadAdapterData() {

       //ArrayList<TypeThreeItem> mArrayList = null;
       // ArrayList<TypeThreeItem> mArrayList = new ArrayList<TypeThreeItem> ();
//        if (getActivity() != null) {
//            mArrayList.add(new TypeThreeItem("Udacity - Lifelong Learning", "Udacity, Inc.", "28 MB", "4.3", R.drawable.ic_launcher));
//            mArrayList.add(new TypeThreeItem("Gmail", "Google LLC", "20 MB", "EDITOR'S CHOICE", R.drawable.ic_launcher));
//            mArrayList.add(new TypeThreeItem("Slack", "Slack Technologies Inc.", "26 MB", "4.4", R.drawable.ic_launcher));
//            mArrayList.add(new TypeThreeItem("LinkedIn", "LinkedIn", "37 MB", "4.2", R.drawable.ic_launcher));
//            mArrayList.add(new TypeThreeItem("Microsoft To-Do", "Microsoft Corporation", "9.9 MB", "4.0", R.drawable.ic_launcher));
//            mArrayList.add(new TypeThreeItem("WhatsApp Messenger", "WhatsApp Inc.", "5.3 MB", "EDITOR'S CHOICE", R.drawable.ic_launcher));
//            mArrayList.add(new TypeThreeItem("Facebook", "Facebook", "12 MB", "4.1", R.drawable.ic_launcher));
//            mArrayList.add(new TypeThreeItem("Twitter", "Twitter, Inc", "39 MB", "4.3", R.drawable.ic_launcher));
//            mArrayList.add(new TypeThreeItem("Code Monk", "Hacker Earth", "6.8 MB", "4.3", R.drawable.ic_launcher));
//
//
//        }


     //   TypeThreeItemAdapter topFreeAppsAdapter = new TypeThreeItemAdapter(mArrayList);
      //  mRecyclerView.setAdapter(topFreeAppsAdapter);
    }
}
