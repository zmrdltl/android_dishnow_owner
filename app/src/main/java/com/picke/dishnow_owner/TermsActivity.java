package com.picke.dishnow_owner;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class TermsActivity extends AppCompatActivity {

    private android.widget.ImageView Iconfirm_check_circle;
    private android.widget.ImageView Iservice_check_circle;
    private android.widget.ImageView Iindividual_check_circle;
    private android.widget.ImageView Ilocation_service_check_circle;
    private android.widget.ImageView Imarketing_receive_check_circle;

    // private ImageView Idot;

    private android.widget.ImageView Iservice_bracket;
    private android.widget.ImageView Iindividual_bracket;
    private android.widget.ImageView Ilocation_service_bracket;
    private android.widget.ImageView Imarketing_recieve_bracket;


    private Button Bconfirm;
    private Button Bservice;
    private Button Bindividual;
    private Button Blocation;
    private Button Bmarketing;

    private Button Bnext;

    int n = 0;
    boolean flag = false;
    boolean ckconfirm = true;
    boolean ckservice = true;
    boolean ckindividual = true;
    boolean cklocation = true;
    boolean ckmarketing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        Toolbar toolbar = findViewById(R.id.terms_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Iconfirm_check_circle = findViewById(R.id.terms_confirm_all_checkcircle);
        Iservice_check_circle = findViewById(R.id.terms_service_checkcircle);
        Iindividual_check_circle = findViewById(R.id.terms_individualinfo_checkcircle);
        Ilocation_service_check_circle = findViewById(R.id.terms_locationservice_checkcircle);
        Imarketing_receive_check_circle = findViewById(R.id.terms_marketingreceive_checkcircle);

        //Idot = findViewById(R.id.terms_smallbutton);


        Iservice_bracket = findViewById(R.id.terms_service_squarebracket);
        Iindividual_bracket = findViewById(R.id.terms_individual_squarebracket);
        Ilocation_service_bracket = findViewById(R.id.terms_locationservice_squarebracket);
        Imarketing_recieve_bracket = findViewById(R.id.terms_marketingrecieve_squarebracket);

        Bconfirm = findViewById(R.id.terms_confirm_all_button);
        Bservice = findViewById(R.id.terms_service_button);
        Bindividual = findViewById(R.id.terms_individual_button);
        Blocation = findViewById(R.id.terms_locationservice_button);
        Bmarketing = findViewById(R.id.terms_marketingreceive_button);

        Bnext = findViewById(R.id.terms_next_button);

        //Idot.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);


        //Checkcircle 너무 작아서 큰 버튼으로 쌋음.시부레
        Bconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ckconfirm)
                {
                    Iconfirm_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                    Iservice_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                    Iindividual_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                    Ilocation_service_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                    Imarketing_receive_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);

                    flag = true;

                    ckconfirm=false;
                    ckservice = false;
                    ckindividual = false;
                    cklocation = false;
                    ckmarketing = false;
                    n=0;
                }
                else
                {
                    Iconfirm_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);
                    Iservice_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);
                    Iindividual_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);
                    Ilocation_service_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);
                    Imarketing_receive_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);

                    flag = false;

                    ckconfirm=true;
                    ckservice = true;
                    ckindividual = true;
                    cklocation = true;
                    ckmarketing = true;
                    n=0;
                }
            }
        });
        Bservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ckservice) {
                    Iservice_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                    ckservice=false;
                    n++;
                    if(n==4) {
                        Iconfirm_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                        if(ckconfirm)
                            ckconfirm=false;
                        else
                            ckconfirm =true;
                    }
                }
                else {
                    Iservice_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);
                    ckservice=true;
                    n--;


                    if(n<4) {
                        Iconfirm_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder), PorterDuff.Mode.SRC_ATOP);
                        ckconfirm=true;
                    }
                }
            }
        });
        Bindividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ckindividual) {
                    Iindividual_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                    ckindividual=false;
                    n++;

                    if(n==4) {
                        Iconfirm_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                        if(ckconfirm)
                            ckconfirm=false;
                        else
                            ckconfirm =true;
                    }

                }

                else {
                    Iindividual_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);
                    ckindividual=true;
                    n--;
                    if(n<4) {
                        Iconfirm_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder), PorterDuff.Mode.SRC_ATOP);
                        ckconfirm=true;
                    }
                }
            }
        });
        
        Blocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cklocation) {
                    Ilocation_service_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                    cklocation=false;
                    n++;
                    if(n==4) {
                        Iconfirm_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet), PorterDuff.Mode.SRC_ATOP);
                        if(ckconfirm)
                            ckconfirm=false;
                        else
                            ckconfirm =true;
                    }
                }
                else {
                    Ilocation_service_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);
                    cklocation=true;
                    n--;
                    if(n<4)
                    {Iconfirm_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);
                        ckconfirm=true;}
                }
            }
        });
        Bmarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ckmarketing) {
                    Imarketing_receive_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet),PorterDuff.Mode.SRC_ATOP);
                    ckmarketing=false;
                    n++;
                    if(n==4) {
                        Iconfirm_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_violet), PorterDuff.Mode.SRC_ATOP);
                        if(ckconfirm)
                            ckconfirm=false;
                        else
                            ckconfirm =true;
                    }
                }


                else {
                    Imarketing_receive_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);
                    ckmarketing=true;
                    n--;
                    if(n<4)
                    { Iconfirm_check_circle.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder),PorterDuff.Mode.SRC_ATOP);
                        ckconfirm=true;
                    }
                }

            }
        });

        // >요렇게 생긴 bracket 눌렀을 때
        Iservice_bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermsActivity.this,TermsServiceActivity.class);
                startActivity(intent);

            }
        });

        Iindividual_bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermsActivity.this,TermsIndividualInfoActivity.class);
                startActivity(intent);

            }
        });

        Ilocation_service_bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermsActivity.this,TermsLocationActivity.class);
                startActivity(intent);

            }
        });

        Imarketing_recieve_bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermsActivity.this,TermsMarketingReceiveActivity.class);
                startActivity(intent);

            }
        });

        ///다음 버튼 클릭시 이벤트
        Bnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==true || (ckservice == false && ckindividual == false && cklocation == false)) {
                    Intent intent = new Intent(TermsActivity.this, JoinActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    public int aasdf(){
    }
    //툴바 뒤로가기 버튼 클릭시
    @Override
    public boolean onOptionsItemSelected(MenuItem item ){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public int adf(MenuItem item){
        return 1;
    }
}