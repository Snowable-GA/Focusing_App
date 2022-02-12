package com.example.focusing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edt_Email, edt_Password;
    private Button btn_Login, btn_Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_Email = findViewById(R.id.edt_email);
        edt_Password = findViewById(R.id.edt_password);
        btn_Login = findViewById(R.id.btn_login);
        btn_Signup = findViewById(R.id.btn_signup);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToDashboard();
            }
        });

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToRegister();
            }
        });

    }

    public void GoToDashboard(){
        String str_Email = edt_Email.getText().toString().trim();
        String str_Password = edt_Password.getText().toString().trim();

        User login_user = new User(" ", " ", " ", " ");
        login_user.setEmail(str_Email);
        login_user.setPassword(str_Password);

        if(login_user.isValidEmail() && login_user.isValidPassword()){
            Intent intent = new Intent(this, DashBoard.class);
            startActivity(intent);
        }
    }

    public void GoToRegister(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}