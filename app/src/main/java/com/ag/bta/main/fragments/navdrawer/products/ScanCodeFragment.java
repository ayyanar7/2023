package com.ag.bta.main.fragments.navdrawer.products;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ag.bta.main.R;
import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeFragment extends Fragment implements ZXingScannerView.ResultHandler {

    int MY_PERMISSIONS_REQUEST_CAMERA=0;
private ArrayList<Integer> mSelectIndices;
    ZXingScannerView scannerView;
Bundle bdl ;
boolean dataAvailable = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(getActivity());
        //bundle
        bdl = getArguments();
        if(bdl !=null) {
            if (!bdl.isEmpty()) {
                dataAvailable = true;
                // bdl.putString("RESULT",result.getText().toString());
            }
        }

        return scannerView;

    }

    @Override
    public void handleResult(Result result) {
        //sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        //sharedViewModel.setName(result.getText().toString());
 if(dataAvailable)
    bdl.putString("ITEM_BARCODE",result.getText().toString());
       // onBackPressed();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragTrans = fm.beginTransaction();
       AddItemFragment add = new AddItemFragment();
       add.setArguments(bdl);
        fragTrans.replace(R.id.homeframelayout, add);
        fragTrans.commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }


}
