package com.example.focusing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    public String ID;
    private EditText edt_Email, edt_Password, edt_Name;
    private Button btn_Register;

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

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_Name = edt_Name.getText().toString().trim();
                String str_Email = edt_Email.getText().toString().trim();
                String str_Password = edt_Password.getText().toString().trim();
                ID = str_Name;
                User signup_user = new User(ID, str_Name, str_Email, str_Password);

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Users");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // on below line we are setting data in our firebase database.
                        databaseReference.child(ID).setValue(signup_user);
                        // displaying a toast message.
                        Toast.makeText(SignUp.this, "User Added..", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // displaying a failure message on below line.
                        Toast.makeText(SignUp.this, "Fail to add user!", Toast.LENGTH_SHORT).show();
                    }
                });

                if(signup_user.isValidEmail() && signup_user.isValidPassword()){
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}