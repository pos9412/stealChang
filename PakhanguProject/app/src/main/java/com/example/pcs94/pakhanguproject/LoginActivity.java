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

    private static final String TAG = "SignUpActivity";       // �±��߰�
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth             // ���̾�̽� ����, �ʱ�ȭ
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.login_button).setOnClickListener(onClickListener);  // Ŭ��
        findViewById(R.id.sign_up_login).setOnClickListener(onClickListener);  // Ŭ��
    }

    @Override            // ���̾�̽� ����
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    public void onBackPressed()      // �ڷΰ����ư ��������, �α׾ƿ��� �ȵ� !
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
                case R.id.login_button :   // ��ư�������� ����
                    signUp();
                 break;
                case R.id.sign_up_login :   // ��ư�������� ����
                    startSignupActivity();
                    break;
            }
        }
    };

    private void signUp() {

        String email = ((EditText)findViewById(R.id.EditText_email)).getText().toString();   // email,password �� ã��
        String password = ((EditText)findViewById(R.id.EditText_password)).getText().toString();


        if(email.length() > 0 && password.length() > 0) {  // ���� ���� �α���
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("�α��ο� ���� �Ͽ����ϴ�.");
                                startMainActivity();     // �α��μ����ϸ� ���̾ƿ� �Ѿ
                            } else {
                                if (task.getException() != null) {
                                    startToast("�̸��� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
                                   // startToast(task.getException().toString());
                                }
                            }
                        }
                    });
        }
        else
        {
            startToast("�̸��� �Ǵ� ��й�ȣ�� �Է� ���ּ���.");
        }

    }

    private void startToast(String msg)    // �佺Ʈ �����Լ�
    {
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
    }


    private void startMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  // �ٽø����ư�����
        startActivity(intent);
    }

    private void startSignupActivity()
    {
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }
}