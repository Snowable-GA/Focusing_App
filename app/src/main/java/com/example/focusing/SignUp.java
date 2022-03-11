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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    public String ID;
    private EditText edt_Email, edt_Password, edt_Name;
    private Button btn_Register;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edt_Name = findViewById(R.id.edt_name);
        edt_Email = findViewById(R.id.edt_email2);
        edt_Password = findViewById(R.id.edt_password2);
        btn_Register = findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_Name.getText().toString().trim();
                String email = edt_Email.getText().toString().trim();
                String password = edt_Password.getText().toString().trim();

                if (name.isEmpty()){
                    edt_Name.setError("Full name is required!");
                    edt_Name.requestFocus();
                    return;
                }

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

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    User user = new User(name, email, password);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(SignUp.this, "User has been registered!", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                                startActivity(intent);
                                            }
                                            else{
                                                Toast.makeText(SignUp.this, "Failed to register!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }

                            }
                        });
            }
        });
    }
}