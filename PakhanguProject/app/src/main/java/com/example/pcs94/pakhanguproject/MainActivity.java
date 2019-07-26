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

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {  // �α��� ���� ���� ���
            startLoginActivity();
        }
                                                                                         //  ��ư ������
        findViewById(R.id.logout_Button).setOnClickListener(onClickListener);      // �ٷι� ������ ��Ʈ
    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logout_Button:         // �α׾ƿ� ��ư������ ���ξ���Ƽ��Ƽ��
                    FirebaseAuth.getInstance().signOut();    // �α׾ƿ� ���
                    startToast("�α׾ƿ� �Ǿ����ϴ�.");
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

    private void startToast(String msg)    // �佺Ʈ �����Լ�
    {
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
    }

}

