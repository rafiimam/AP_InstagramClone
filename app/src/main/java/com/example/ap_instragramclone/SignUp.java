package com.example.ap_instragramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtText1,edtText2,edtText3,edtText4,edtText5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUp.this);

        edtText1 = findViewById(R.id.edtText1);
        edtText2 = findViewById(R.id.edtText2);
        edtText3 = findViewById(R.id.edtText3);
        edtText4 = findViewById(R.id.edtText4);
        edtText5 = findViewById(R.id.edtText5);
    }

    @Override
    public void onClick(View v) {

        try {

            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtText1.getText().toString());
            kickBoxer.put("Punch_Speed", Integer.parseInt(edtText2.getText().toString()));
            kickBoxer.put("Punch_Power", Integer.parseInt(edtText3.getText().toString()));
            kickBoxer.put("Kick_Speed", Integer.parseInt(edtText4.getText().toString()));
            kickBoxer.put("Kick_Power", Integer.parseInt(edtText5.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is saved !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name ") + "is not saved because of " + e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        } catch (Exception e){FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();}
    }
}
