package com.example.ex18_login_register_sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {

    private final AppCompatActivity activity = register.this;

    private  DBHelper databaseHelper;
    private validate inputValidation;
    private User user;

    Button btnRegister;
    EditText txtName,txtEmail,txtUsername,txtPassword,txtConfirmPassword;
    TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


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
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveDataToSQLite()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

private boolean saveDataToSQLite() {
    if (!inputValidation.isInputEditTextFilled(txtName)) {
        Toast.makeText(getApplicationContext(), "กรุณาใส่นามสกุล", Toast.LENGTH_SHORT).show();
        return false;
    }
    if (!inputValidation.isInputEditTextFilled(txtEmail)) {
        Toast.makeText(getApplicationContext(), "กรุณาใส่อีเมลล์หรือรูปแบบผิด", Toast.LENGTH_SHORT).show();
        return false;
    }
    if (!inputValidation.isInputEditTextFilled(txtUsername)) {
        Toast.makeText(getApplicationContext(), "กรุณาใส่ชื่อผู้ใช้", Toast.LENGTH_SHORT).show();
        return false;
    }
    if (!inputValidation.isInputEditTextFilled(txtPassword)) {
        Toast.makeText(getApplicationContext(), "กรุณาใส่รหัสผ่าน", Toast.LENGTH_SHORT).show();
        return false;
    }
    if (!inputValidation.isInputEditTextFilled(txtConfirmPassword)) {
        Toast.makeText(getApplicationContext(), "กรุณาใส่รหัสผ่านยืนยัน", Toast.LENGTH_SHORT).show();
        return false;
    }
    if (inputValidation.isInputEditTextMatches(txtPassword, txtConfirmPassword)) {
        Toast.makeText(getApplicationContext(), "รหัสผ่านยืนยันไม่ตรงกัน", Toast.LENGTH_SHORT).show();
        return false;
    }

    if (!databaseHelper.checkUser(txtUsername.getText().toString().trim(), txtPassword.getText().toString().trim())) {
        user.setName(txtName.getText().toString().trim());
        user.setEmail(txtEmail.getText().toString().trim());
        user.setUsername(txtUsername.getText().toString().trim());
        user.setPassword(txtPassword.getText().toString().trim().trim());
        databaseHelper.addUser(user);
        Toast.makeText(getApplicationContext(), "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
        clearText();
        return true;
    } else {
        Toast.makeText(getApplicationContext(), "เกิดการผิดพลาด ไม่สามารถบันทึกข้อมูลได้", Toast.LENGTH_SHORT).show();
        return false;
    }
}
    private void clearText() {
                 txtName.setText(null);
                 txtEmail.setText(null);
                 txtUsername.setText(null);
                 txtPassword.setText(null);
                 txtConfirmPassword.setText(null);
    }


    private void initViews() {
        btnRegister = findViewById(R.id.btnRegister);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        txtMessage = findViewById(R.id.txtMessage);
    }
}

