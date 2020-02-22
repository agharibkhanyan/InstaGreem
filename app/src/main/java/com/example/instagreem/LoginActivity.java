package com.example.instagreem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.instagreem.fragments.SignUp;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import static kotlinx.coroutines.android.HandlerDispatcherKt.Main;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG ="LoginActivity";

    private EditText    etUsername;
    private EditText    etPassword;
    private Button      btnLogin;
    private Button      btnSignUp;
    private EditText    etUser;
    private EditText    etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ParseYser currentUser = ParseUser.getCurrentUser();
        //if(currentUser != null)
            //goMainActivity();

        //Button btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e(TAG,"clicked");
                goSignUp();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username,password);
            }
        });

    }

    private void login(String username, String password){
        //navigate to new activity
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null){
                    Log.e(TAG,"Issue with login");
                    e.printStackTrace();
                    return;
                }
                //navigate to new activity
                goMainActivity();
            }
        });
    }


    private void goSignUp(){
        Fragment  fragment;
        final FragmentManager fragmentManager = getSupportFragmentManager();
        //Intent i = new Intent(this, SignUp.class);
        //startActivity(i);
        fragment = new SignUp();
        fragmentManager.beginTransaction().replace(R.id.flContainer,fragment).commit();


    }

    private void signUp(String user,String pass,String email){
       // Parse.

    }

    private void goMainActivity() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
