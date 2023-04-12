package com.ag.bta.main.fragments.navdrawer.products;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ag.bta.main.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteItemsFragment extends Fragment {
    public static TextView resultdeleteview;
    private FirebaseAuth firebaseAuth;
    Button scantodelete, deletebtn;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.inventories_activity_delete_items, null);


        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        resultdeleteview = view.findViewById(R.id.barcodedelete);
        scantodelete = view.findViewById(R.id.buttonscandelete);
        deletebtn= view.findViewById(R.id.deleteItemToTheDatabasebtn);

        scantodelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ScanCodeActivitydel.class));
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletefrmdatabase(v);
            }
        });

    }

    public void deletefrmdatabase(View v)
    {
        String deletebarcodevalue = resultdeleteview.getText().toString();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser=users.getEmail();
        String resultemail = finaluser.replace(".","");
        if(!TextUtils.isEmpty(deletebarcodevalue)){
            databaseReference.child(resultemail).child("Items").child(deletebarcodevalue).removeValue();
            Toast.makeText(v.getContext(),"Item is Deleted",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(v.getContext(),"Please scan Barcode",Toast.LENGTH_SHORT).show();
        }
    }
}
