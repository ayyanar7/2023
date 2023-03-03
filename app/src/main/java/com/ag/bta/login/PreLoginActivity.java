package com.ag.bta.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ag.bta.main.R;
import com.ag.bta.main.activities.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PreLoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);
        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        if(user != null){

            startActivity(new Intent(this, HomeActivity.class));
            finish(); //need change
        }

    }


    public void login (View view)
    {

        startActivity(new Intent(this,LoginActivity.class));
//        String TextClassname = classname.getText().toString();
//        // starting our intent
//        Intent classintent = new Intent(this,SecondActivity.class);
//        classintent.putExtra("Classname",TextClassname);
//        startActivityForResult(classintent,request_code);
    }
    public void register (View view)
    {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}

