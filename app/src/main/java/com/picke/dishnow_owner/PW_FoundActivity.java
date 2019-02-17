package com.picke.dishnow_owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PW_FoundActivity extends AppCompatActivity {

    private Button Bgobackbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw__found);

        Bgobackbutton = findViewById(R.id.repass2_gobackbutton);

        Bgobackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PW_FoundActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
