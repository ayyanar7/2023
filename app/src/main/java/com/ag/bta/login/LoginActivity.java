package com.ag.bta.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ag.bta.constants.Constants;
import com.ag.bta.main.R;
import com.ag.bta.main.activities.HomeActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    //private Button Login;
   // private TextView passwordreset;
    private EditText passwordresetemail;
    private ProgressBar progressBar;

    private FirebaseAuth auth;
    private ProgressDialog processDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_login_screen);
Log.d(Constants.STR_APP_ID, "Inside login on create");
        Email = (EditText) findViewById(R.id.emailSignIn);
        Password = (EditText) findViewById(R.id.password);
          Button Login = (Button) findViewById(R.id.Login);

          TextView  passwordreset = (TextView)findViewById(R.id.forgotpassword);
        passwordresetemail = findViewById(R.id.emailSignIn);
        progressBar = (ProgressBar) findViewById(R.id.progressbars);
        progressBar.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        Log.d(Constants.STR_APP_ID, "Firebase instance: "+auth);
        processDialog = new ProgressDialog(this);



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
            }

        });

        passwordreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpasword();
            }
        });
    }

    public void resetpasword(){
        final String resetemail = passwordresetemail.getText().toString();

        if (resetemail.isEmpty()) {
            passwordresetemail.setError("It's empty");
            passwordresetemail.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(resetemail)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }




    public void validate(String userEmail, String userPassword){
        Log.d(Constants.STR_APP_ID, "Validating ...");
        Log.d(Constants.STR_APP_ID, "Email: "+userEmail);
        Log.d(Constants.STR_APP_ID, "Password: "+userPassword);
        processDialog.setMessage("................Please Wait.............");
        processDialog.show();

        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(Constants.STR_APP_ID, "Signin with email pass...");
                Log.d(Constants.STR_APP_ID, "task: getStackTrace"+task.getException());
                Log.d(Constants.STR_APP_ID, "task: "+task.getResult());
                if(task.isSuccessful()){

                    processDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,  HomeActivity.class));
                }

                else{
                    Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    processDialog.dismiss();

                }
            }
        });
    }
}
