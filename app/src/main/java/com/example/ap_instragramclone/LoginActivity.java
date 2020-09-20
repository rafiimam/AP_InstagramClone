package com.example.ap_instragramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin2, btnSigup2;
    private EditText edtEmailLogin, edtPasswordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");

        btnLogin2 = findViewById(R.id.btnLogin2);
        btnSigup2 = findViewById(R.id.btnSignup2);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswrodLogin);

        btnLogin2.setOnClickListener(this);
        btnSigup2.setOnClickListener(this);

        edtPasswordLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnLogin2);
                }
                return false;
            }
        });

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLogin2:

                if (edtEmailLogin.getText().toString().equals("")||
                        edtPasswordLogin.getText().toString().equals(""))
                {
                    FancyToast.makeText(LoginActivity.this, "E-mail, Password Is Required", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                }
                else {

                    ParseUser.logInInBackground(edtEmailLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(LoginActivity.this, user.get("username") + " is logged in successfully !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(LoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }

                break;
            case R.id.btnSignup2:

                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);


                break;
        }

    }

    public void rootLayoutt(View view) {

        try {
            {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}