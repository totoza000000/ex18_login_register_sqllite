package com.example.ex18_login_register_sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private  final AppCompatActivity activity = login.this;

    private DBHelper databaseHelper;
    private validate inputValidation;
    private User user;

    Button btnLogin, btnRegister;
    EditText txtUsername, txtPassword;
    TextView txtMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initEvent();
        initObjects();
    }

    private void initObjects() {
        databaseHelper = new DBHelper(activity);
        inputValidation = new validate(activity);
        user = new User();
    }


    private void initEvent() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckLogin()) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }

        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),register.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtMessage = findViewById(R.id.txtMessage);
    }

    private boolean CheckLogin() {
        if (!inputValidation.isInputEditTextFilled(txtUsername)){
            Toast.makeText(getApplicationContext(),"กรุณาใส่ชื่อผู้ใช้",Toast.LENGTH_SHORT).show();
            return false;
    }
        if (!inputValidation.isInputEditTextFilled(txtPassword)){
            Toast.makeText(getApplicationContext(),"กรุณาใส่รหัสผ่าน",Toast.LENGTH_SHORT).show();
            return false;
    }
        if (databaseHelper.checkUser(txtUsername.getText().toString().trim(),txtPassword.getText().toString().trim())){
            return false;
    }else {
            Toast.makeText(getApplicationContext(),"ชื่อผู้ใช้หรือรหัสผ่านไม่ถูต้อง",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}