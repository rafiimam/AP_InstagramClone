package com.example.ap_instragramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignup, btnLogin;
    private EditText edtEmail,edtUserNameSignup,edtPasswordSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        edtEmail = findViewById(R.id.edtEmail);
        edtUserNameSignup = findViewById(R.id.edtUserNameSignup);
        edtPasswordSignup = findViewById(R.id.edtPasswordSigup);

        edtPasswordSignup.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnSignup);
                }
                return false;
            }
        });

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSignup:

            if (edtUserNameSignup.getText().toString().equals("")||
                    edtEmail.getText().toString().equals("")||
                    edtPasswordSignup.getText().toString().equals(""))
            {
                FancyToast.makeText(SignUp.this, "E-mail, Username, Password Is Required", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
            }
            else {


                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUserNameSignup.getText().toString());
                    appUser.setPassword(edtPasswordSignup.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtUserNameSignup.getText().toString());
                    progressDialog.show();
                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null)
                            {FancyToast.makeText(SignUp.this, appUser.get("username") + " is Signed up successfully !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            transitionToSocialMediaActivity();
                            }
                            else
                            {FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();}
                            progressDialog.dismiss();
                        }
                    });


            }
                break;
            case R.id.btnLogin:

                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);

                break;

        }

    }

    public void rootLayout(View view){

        try {
            {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(SignUp.this,SocialMediaActivity.class);
        startActivity(intent);
    }

}
