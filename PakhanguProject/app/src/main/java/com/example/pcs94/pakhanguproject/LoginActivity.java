package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";       // 태그추가
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth             // 파이어베이스 복붙, 초기화
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.login_button).setOnClickListener(onClickListener);  // 클릭
        findViewById(R.id.sign_up_login).setOnClickListener(onClickListener);  // 클릭
    }

    @Override            // 파이어베이스 복붙
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    public void onBackPressed()      // 뒤로가기버튼 어플종료, 로그아웃은 안됨 !
    {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login_button :   // 버튼눌렀을때 로직
                    signUp();
                 break;
                case R.id.sign_up_login :   // 버튼눌렀을때 로직
                    startSignupActivity();
                    break;
            }
        }
    };

    private void signUp() {

        String email = ((EditText)findViewById(R.id.EditText_email)).getText().toString();   // email,password 값 찾기
        String password = ((EditText)findViewById(R.id.EditText_password)).getText().toString();


        if(email.length() > 0 && password.length() > 0) {  // 기존 유저 로그인
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인에 성공 하였습니다.");
                                startMainActivity();     // 로그인성공하면 레이아웃 넘어감
                            } else {
                                if (task.getException() != null) {
                                    startToast("이메일 또는 비밀번호가 틀렸습니다.");
                                   // startToast(task.getException().toString());
                                }
                            }
                        }
                    });
        }
        else
        {
            startToast("이메일 또는 비밀번호를 입력 해주세요.");
        }

    }

    private void startToast(String msg)    // 토스트 생성함수
    {
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
    }


    private void startMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  // 다시못돌아가게함
        startActivity(intent);
    }

    private void startSignupActivity()
    {
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }
}