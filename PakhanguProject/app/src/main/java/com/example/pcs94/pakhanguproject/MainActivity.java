package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {  // 로그인 유지 관련 기능
            startLoginActivity();
        }
                                                                                         //  버튼 누르기
        findViewById(R.id.logout_Button).setOnClickListener(onClickListener);      // 바로밑 문법과 세트
    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logout_Button:         // 로그아웃 버튼누를시 사인업액티비티로
                    FirebaseAuth.getInstance().signOut();    // 로그아웃 기능
                    startToast("로그아웃 되었습니다.");
                    startLoginActivity();
                    break;

            }
        }
    };

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void startToast(String msg)    // 토스트 생성함수
    {
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
    }

}

