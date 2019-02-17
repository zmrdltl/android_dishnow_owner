package com.picke.dishnow_owner;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.kakao.usermgmt.response.model.User;
import com.picke.dishnow_owner.Owner_User.UserAuthClass;
import com.picke.dishnow_owner.Utility.VolleySingleton;

public class Additional_InfoActivity extends AppCompatActivity {

    private UserAuthClass userAuthClass;
    private EditText Eownerbirth;
    private RadioButton Rsex_man;
    private RadioButton Rsex_woman;
    private Button BnextButton;
    private String Ssex="man";
    private TextView Tsex_man;
    private TextView Tsex_woman;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional__info);

        Eownerbirth = findViewById(R.id.additional_info__ownerbirth);
        Rsex_man = findViewById(R.id.additional_info_sex_man_radiobutton);
        Rsex_woman = findViewById(R.id.additional_info_sex_woman_radiobutton);
        BnextButton = findViewById(R.id.additional_info_next_button);
        radioGroup = findViewById(R.id.additional_info__radiogroup);
        Tsex_man = findViewById(R.id.additional_info__sex_man_text);
        Tsex_woman = findViewById(R.id.additional_info__sex_woman_text);

        Eownerbirth.getBackground().setColorFilter(getResources().getColor(R.color.color_bolder), PorterDuff.Mode.SRC_ATOP);

        Toolbar toolbar = findViewById(R.id.additional_info__toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userAuthClass = UserAuthClass.getInstance(getApplicationContext());

        RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new
                RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(android.widget.RadioGroup group, int checkedId) {
                        if(checkedId == R.id.additional_info_sex_man_radiobutton){
                            Ssex="man";
                            Tsex_man.setTextColor(getResources().getColor(R.color.color_violet));
                            Tsex_woman.setTextColor(getResources().getColor(R.color.color_bolder));

                        }else{
                            Ssex="woman";
                            Tsex_man.setTextColor(getResources().getColor(R.color.color_bolder));
                            Tsex_woman.setTextColor(getResources().getColor(R.color.color_violet));
                        }
                    }
                };
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        BnextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAuthClass.setOwnersex(Ssex);
                userAuthClass.setOwnerbirth(Eownerbirth.getText().toString());
                Toast.makeText(getApplicationContext(),userAuthClass.getOwnerbirth(),Toast.LENGTH_LONG).show();
                startActivity(new Intent(Additional_InfoActivity.this,VerificationActivity.class));
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
