package com.example.ap_instragramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    public Button btnSignUp,btnLogin;
    public EditText edtUserName,edtPassword,edtUserNameLogin,edtPasswordLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_login_activity);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtUserName = findViewById(R.id.edtUserName);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPassword = findViewById(R.id.edtPassword);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserName.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){

                            FancyToast.makeText(SignUpLoginActivity.this, appUser.get("username") + " is signed up successfully !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent = new Intent(SignUpLoginActivity.this,WelcomeActivity.class);
                            startActivity(intent);
                        }
                        else {FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();}
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){


                            FancyToast.makeText(SignUpLoginActivity.this, user.get("username") + " is logged in successfully !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            Intent intent = new Intent(SignUpLoginActivity.this,WelcomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });
    }
}
