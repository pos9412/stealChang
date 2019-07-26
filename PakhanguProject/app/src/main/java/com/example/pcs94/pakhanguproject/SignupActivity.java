package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";       // �±��߰�
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth             // ���̾�̽� ����, �ʱ�ȭ
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.sign_up_button).setOnClickListener(onClickListener);  // Ŭ��
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.sign_up_button :   // ��ư�������� ����
                    signUp();
                 break;
            }
        }
    };

    private void signUp() {    // ȸ������ ���

        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();   // email,password �� ã��
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.passwordCheckEditText)).getText().toString();


        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0) {  // �Է��� �Ǿ��־�� ����
            if (password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)         // ���̾�̽� ����
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startToast("ȸ�������� �Ϸ� �Ǿ����ϴ�.");
                                    startLoginActivity();
                                    //���� UI ����
                                } else {
                                    //���� UI ����
                                    if (task.getException() != null) {
                                        startToast("�̸��� ������ Ʋ�Ƚ��ϴ�.");
                                        //startToast(task.getException().toString());
                                    }
                                }

                            }
                        });
            }
            else
            {
                startToast("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
            }
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

    private void startLoginActivity()
    {
        Intent intent = new Intent(this,LoginActivity.class);  // ���̾ƿ� �ѱ��
        intent .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  // ��Ƽ��Ƽ �ѱ�� �ٽø����ư�����
        FirebaseAuth.getInstance().signOut();    // �α׾ƿ� ���, ����� ȸ�������� ���������ص� �ٽ�Ű�� �α��ε�
        startActivity(intent);
    }
}