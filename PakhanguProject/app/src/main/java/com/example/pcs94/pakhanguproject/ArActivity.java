package com.example.pcs94.pakhanguproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ArActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        Intent intent = new Intent(getApplication(), CarinfoActivity.class);
        startActivity(intent);
        finish();


    }
}
