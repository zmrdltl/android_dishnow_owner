package com.picke.dishnow_owner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.picke.dishnow_owner.Owner_User.UserAuthClass;
import com.picke.dishnow_owner.Owner_User.UserInfoClass;
import com.picke.dishnow_owner.Utility.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FindidpassActivity extends AppCompatActivity {

    private EditText Eownername;
    private EditText Eownerphonenum;
    private EditText Echecknum;
    private Button Bsendchecknum;
    private Button Bfindemail;
    private RequestQueue requestQueue;
    private String Authnumber="";
    private UserAuthClass userAuthClass;
    private UserInfoClass userInfoClass;
    private String uid;
    private TextView Tnomatcherror;
    private TextView Tnomatcherror1;

    final String feed_url="http://claor123.cafe24.com/Findemail3.php";
    final String feed_url2="http://claor123.cafe24.com/Re1pass3.php";

    private Button Bsendchecknum2;
    private EditText Eowneremail;
    private EditText Eownerphonenum2;
    private EditText Eownername2;
    private EditText Echecknum2;
    private Button Brepassnextbutton;

    private Boolean flag1=false,flag2=false;

    private String findemail_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findidpass);
        Eownername = findViewById(R.id.ownername);
        Eownerphonenum = findViewById(R.id.ownerphonenum);
        Echecknum = findViewById(R.id.checknum);
        Bsendchecknum = findViewById(R.id.sendchecknumbutton);
        Bfindemail = findViewById(R.id.findemailbutton);
        final Context context = this;

        Bsendchecknum2 = findViewById(R.id.findidpass_sendchecknumbutton2);
        Eowneremail = findViewById(R.id.findidpass_owneremail);
        Eownername2 = findViewById(R.id.findidpass_ownername2);
        Eownerphonenum2 = findViewById(R.id.findidpass_ownerphonenum2);
        Echecknum2 = findViewById(R.id.findidpass_checknum2);
        Brepassnextbutton = findViewById(R.id.findidpass_nextbutton2);

        Tnomatcherror = findViewById(R.id.findidpass_errormessage);
        Tnomatcherror1 = findViewById(R.id.findidpass_errormessage1);

        userAuthClass = UserAuthClass.getInstance(getApplicationContext());
        // 뒤로가기 버튼
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.findemail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("이메일 찾기") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("비밀번호 재설정") ;
        tabHost1.addTab(ts2) ;

        Drawable image_ok = getApplicationContext().getResources().getDrawable(R.drawable.ic_iconmonstr_check_mark_15);
        image_ok.setBounds(60,0,0,0);
        Drawable image_no = getApplicationContext().getResources().getDrawable(R.drawable.ic_icon_x);
        image_no.setColorFilter(getResources().getColor(R.color.color_red),PorterDuff.Mode.SRC_ATOP);
        image_no.setBounds(60,0,0,0);

        requestQueue = VolleySingleton.getmInstance(getApplicationContext()).getRequestQueue();
        final StringRequest stringRequest_phone = new StringRequest(Request.Method.POST, feed_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    findemail_id = jsonObject.getString("owner_id");

                    if(success==true){

                        flag2=true;
                        Tnomatcherror1.setText("");
                        Eownerphonenum.setCompoundDrawablesWithIntrinsicBounds(null,null,image_ok,null);
                        Eownerphonenum.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder
                                .setMessage("인증번호가 전송되었습니다.")
                                .setPositiveButton("확인",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        Button btn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                    } else{
                        Tnomatcherror1.setText("일치하는 정보가 없습니다");
                        Eownerphonenum.setCompoundDrawablesWithIntrinsicBounds(null, null, image_no, null);
                        Eownerphonenum.getBackground().setColorFilter(getResources().getColor(R.color.color_red), PorterDuff.Mode.SRC_ATOP);
                        flag2=false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("m_phone",Eownerphonenum.getText().toString());
                params.put("m_ownername", Eownername.getText().toString());
                params.put("m_number",Authnumber);
                return params;
            }
        };

        final StringRequest stringRequest_phone2 = new StringRequest(Request.Method.POST, feed_url2, new Response.Listener<String>() {
            @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if(success==true){
                            flag1=true;
                            Tnomatcherror.setText("");
                            Eownerphonenum2.setCompoundDrawablesWithIntrinsicBounds(null,null,image_ok,null);
                            Eownerphonenum2.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);

                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder
                                    .setMessage("인증번호가 전송되었습니다.")
                                    .setPositiveButton("확인",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                }
                                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                            Button btn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                        }
                        else if(success==false) {
                            Tnomatcherror.setText("일치하는 정보가 없습니다");
                            Eownerphonenum2.setCompoundDrawablesWithIntrinsicBounds(null, null, image_no, null);
                            Eownerphonenum2.getBackground().setColorFilter(getResources().getColor(R.color.color_red), PorterDuff.Mode.SRC_ATOP);
                            flag1=false;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected  Map<String, String> getParams() throws  AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("m_ownername", Eownername2.getText().toString());
                params.put("m_owneremail", Eowneremail.getText().toString());
                params.put("m_phone", Eownerphonenum2.getText().toString());
                params.put("m_number", Authnumber);
                return params;
            }
       };

        Bsendchecknum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Random random = new Random();
                        Authnumber = "";
                        for (int i = 0; i < 4; i++) {
                            Authnumber += Integer.toString(random.nextInt(10));
                        }
                        requestQueue.add(stringRequest_phone);
                    }
                });

        Bsendchecknum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Random random = new Random();
                    Authnumber = "";
                    for (int i = 0; i < 4; i++) {
                        Authnumber += Integer.toString((random.nextInt(10)));
                    }
                    requestQueue.add(stringRequest_phone2);
            }
        });

        Bfindemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2==false){

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder
                            .setMessage("인증번호를 전송해야합니다.")
                            .setPositiveButton("확인",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    Button btn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                }
                else if(Authnumber.equals(Echecknum.getText().toString())){
                    userAuthClass.setOwnername(Eownername.getText().toString());
                    userAuthClass.setOwnerid(findemail_id);
                    Intent intent1 = new Intent(FindidpassActivity.this, FindemailActivity.class);
                    startActivity(intent1);
                    finish();
                }else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder
                            .setMessage("인증번호가 일치하지 않습니다.")
                            .setPositiveButton("확인",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    Button btn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                }
            }
        });

        Brepassnextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1==false){

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder
                            .setMessage("인증번호를 전송해야합니다.")
                            .setPositiveButton("확인",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    Button btn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                }
                else if(Authnumber.equals(Echecknum2.getText().toString())){
                    userAuthClass.setOwnerid(Eowneremail.getText().toString());
                    userAuthClass.setOwnername(Eownername2.getText().toString());
                    userAuthClass.setOwnerphone(Eownerphonenum2.getText().toString());
                    Intent intent = new Intent(FindidpassActivity.this, Change_PWActivity.class);
                    startActivity(intent);
                    finish();
                }else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder
                            .setMessage("인증번호가 일치하지 않습니다.")
                            .setPositiveButton("확인",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    Button btn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
