package com.picke.dishnow_owner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.picke.dishnow_owner.Owner_User.UserAuthClass;
import com.picke.dishnow_owner.Utility.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Change_PWActivity extends AppCompatActivity {

    private EditText Erepass1;
    private EditText Erepass2;
    private TextView Trepasserror1;
    private TextView Trepasserror2;
    private Button Brepassbutton;

    private RequestQueue requestQueue;

    private UserAuthClass userAuthClass;

    private Boolean flag2=false,flag3=false;

    final String feed_url="http://claor123.cafe24.com/Newpass3.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__pw);

        Erepass1 = findViewById(R.id.repass_pass1);
        Erepass2 = findViewById(R.id.repass_pass2);
        Trepasserror1 = findViewById(R.id.change_pw_passworderror);
        Trepasserror2 = findViewById(R.id.change_pw_passnotmatch);
        Brepassbutton = findViewById(R.id.repass_button);
        final Context context = this;

        userAuthClass = UserAuthClass.getInstance(getApplicationContext());

        requestQueue = VolleySingleton.getmInstance(getApplicationContext()).getRequestQueue();
        final StringRequest stringRequest_repass = new StringRequest(Request.Method.POST, feed_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("m_id", userAuthClass.getOwnerid());
                params.put("m_password", Erepass1.getText().toString());
                return params;
            }
        };

        Drawable image_ok = getApplicationContext().getResources().getDrawable(R.drawable.ic_iconmonstr_check_mark_15);
        image_ok.setBounds(60,0,0,0);
        Drawable image_no = getApplicationContext().getResources().getDrawable(R.drawable.ic_icon_x);
        image_no.setColorFilter(getResources().getColor(R.color.color_red),PorterDuff.Mode.SRC_ATOP);
        image_no.setBounds(60,0,0,0);

        Erepass1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!Pattern.matches("^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,20}$", Erepass1.getText().toString())){
                    Trepasserror1.setText("영문, 숫자 8~20자의 비밀번호를 설정하세요.");
                    Erepass1.getBackground().setColorFilter(getResources().getColor(R.color.color_red),PorterDuff.Mode.SRC_ATOP);
                    Erepass1.setCompoundDrawablesWithIntrinsicBounds(null,null,image_no,null);
                    flag2=false;
                }else if(Erepass1.getText().toString().length()!=0){
                    Trepasserror1.setText("");
                    Erepass1.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                    Erepass1.setCompoundDrawablesWithIntrinsicBounds(null,null,image_ok,null);
                    flag2=true;
                }
            }
        });

        Erepass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if((Erepass1.getText().toString()).equals(Erepass2.getText().toString())){
                    Trepasserror2.setText("");
                    Erepass2.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                    flag3=true;
                    Erepass2.setCompoundDrawablesWithIntrinsicBounds(null,null,image_ok,null);
                }else if(Erepass2.getText().toString().length()!=0){
                    Trepasserror2.setText("비밀번호가 일치하지 않습니다.");
                    Erepass2.setCompoundDrawablesWithIntrinsicBounds(null,null,image_no,null);
                    Erepass2.getBackground().setColorFilter(getResources().getColor(R.color.color_red),PorterDuff.Mode.SRC_ATOP);
                    flag3=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Brepassbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2==true&&flag3==true) {
                    requestQueue.add(stringRequest_repass);
                    userAuthClass.setOwnerpassword(Erepass1.getText().toString());

                    Intent intent = new Intent(Change_PWActivity.this, PW_FoundActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "비밀번호 재설정을 확인해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}