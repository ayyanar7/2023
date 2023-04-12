package com.ag.bta.main.fragments.navdrawer.products;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ag.bta.main.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemFragment extends Fragment {
    private EditText itemname,itemcategory,itemprice;
    private TextView itembarcode;
    private FirebaseAuth firebaseAuth;
    public   TextView resulttextview;
    Button scanbutton, additemtodatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferencecat;

    boolean dataAvailable = false;
    Bundle bdl = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.inventories_activity_additem, null);

          bdl = getArguments();
        if(bdl !=null) {
            if (!bdl.isEmpty()) {
                dataAvailable = true;
               // bdl.putString("RESULT",result.getText().toString());
            }
        }else{
            bdl = new Bundle();
            dataAvailable = false;
        }

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReferencecat = FirebaseDatabase.getInstance().getReference("Users");
        resulttextview = view.findViewById(R.id.barcodeview);
        additemtodatabase = view.findViewById(R.id.additembuttontodatabase);
        scanbutton = view.findViewById(R.id.buttonscan);
        itemname = view.findViewById(R.id.edititemname);
        itemcategory= view.findViewById(R.id.editcategory);
        itemprice = view.findViewById(R.id.editprice);
        itembarcode= view.findViewById(R.id.barcodeview);

if(dataAvailable){
    itemname.setText( bdl.getString("ITEM_NAME"));
    itemcategory.setText(bdl.getString("ITEM_CATEGORY"));
    itemprice.setText(bdl.getString("ITEM_PRICE"));
    resulttextview.setText(bdl.getString("ITEM_BARCODE"));
}
       // String result = finaluser.substring(0, finaluser.indexOf("@"));

        scanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemnameValue = itemname.getText().toString();
                String itemcategoryValue = itemcategory.getText().toString();
                String itempriceValue = itemprice.getText().toString();
                String itembarcodeValue = itembarcode.getText().toString();
                if(bdl != null) {
                    bdl.putString("ITEM_NAME", itemnameValue);
                    bdl.putString("ITEM_CATEGORY", itemcategoryValue);
                    bdl.putString("ITEM_PRICE", itempriceValue);
                    bdl.putString("ITEM_BARCODE", itembarcodeValue);
                }

                // onBackPressed();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragTrans = fm.beginTransaction();
                ScanCodeFragment scan = new ScanCodeFragment();
                scan.setArguments(bdl);
                fragTrans.replace(R.id.homeframelayout, scan);
                fragTrans.commit();
            }
        });

        additemtodatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additem(v);


            }
        });

    }


// addding item to databse
public  void additem(View v){
        String itemnameValue = itemname.getText().toString();
        String itemcategoryValue = itemcategory.getText().toString();
        String itempriceValue = itemprice.getText().toString();
        String itembarcodeValue = itembarcode.getText().toString();


        // final FirebaseUser users = firebaseAuth.getCurrentUser();
        //String finaluser=users.getEmail();
        // String resultemail = finaluser.replace(".","");
    if (itembarcodeValue.isEmpty()) {
        itembarcode.setError("It's Empty");
        itembarcode.requestFocus();
        return;
    }


    if(!TextUtils.isEmpty(itemnameValue)&&!TextUtils.isEmpty(itemcategoryValue)&&!TextUtils.isEmpty(itempriceValue)){

        Items items = new Items(itemnameValue,itemcategoryValue,itempriceValue,itembarcodeValue);
        //databaseReference.child(resultemail).child("Items").child(itembarcodeValue).setValue(items);
       // databaseReferencecat.child(resultemail).child("ItemByCategory").child(itemcategoryValue).child(itembarcodeValue).setValue(items);
        itemname.setText("");
        itembarcode.setText("");
        itemprice.setText("");
        itembarcode.setText("");
        Toast.makeText(v.getContext(),itemnameValue+" Added",Toast.LENGTH_SHORT).show();
    }
    else {
        Toast.makeText(v.getContext(),"Please Fill all the fields",Toast.LENGTH_SHORT).show();
    }
}





}
