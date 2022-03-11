package com.example.focusing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edt_Email, edt_Password;
    private Button btn_Login, btn_SignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_Email = findViewById(R.id.edt_email);
        edt_Password = findViewById(R.id.edt_password);
        btn_Login = findViewById(R.id.btn_login);
        btn_SignUp = findViewById(R.id.btn_signup);
        mAuth = FirebaseAuth.getInstance();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_Email.getText().toString().trim();
                String password = edt_Password.getText().toString().trim();

                if (email.isEmpty()){
                    edt_Email.setError("Email is required!");
                    edt_Email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    edt_Email.setError("Please provide valid email!");
                    edt_Email.requestFocus();
                    return;
                }

                if (password.isEmpty()){
                    edt_Password.setError("Password is required!");
                    edt_Password.requestFocus();
                    return;
                }

                if (password.length() <= 6){
                    edt_Password.setError("Your password is too short!");
                    edt_Password.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                    if (user.isEmailVerified()){
                                        startActivity(new Intent(MainActivity.this, DashBoard.class));
                                    }
                                    else{
                                        user.sendEmailVerification();
                                        Toast.makeText(MainActivity.this, "Check your email to verify!", Toast.LENGTH_LONG).show();
                                    }
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Failed to login!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}