package com.picke.dishnow_owner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.picke.dishnow_owner.Owner_User.UserAuthClass;
import com.picke.dishnow_owner.Utility.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class FindemailActivity extends AppCompatActivity {

    private Button Bgobackbutton;
    private TextView Townername;
    private TextView Towneremail;
    private UserAuthClass userAuthClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findemail);

        Bgobackbutton = findViewById(R.id.findemail_gobackbutton);
        Towneremail = findViewById(R.id.findemail_auth_email);
        Townername = findViewById(R.id.findemail_auth_name);

        userAuthClass = UserAuthClass.getInstance(getApplicationContext());

        Townername.setText(userAuthClass.getOwnername());
        Towneremail.setText(userAuthClass.getOwnerid());

        Bgobackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindemailActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }
}