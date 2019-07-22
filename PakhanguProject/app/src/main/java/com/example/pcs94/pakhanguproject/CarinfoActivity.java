package com.example.pcs94.pakhanguproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CarinfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carinfo);

        Button Gologin = (Button)findViewById(R.id.Gologin);

        Gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarinfoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }
}
