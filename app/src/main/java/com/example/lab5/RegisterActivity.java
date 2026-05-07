package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText e1, e2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        e1 = findViewById(R.id.editTextEmail);
        e2 = findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    public void registerUser(View v){

        if(e1.getText().toString().equals("") &&
                e2.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(),
                    "Blank not allowed",
                    Toast.LENGTH_SHORT).show();

        }else{

            mAuth.createUserWithEmailAndPassword(
                            e1.getText().toString(),
                            e2.getText().toString())

                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(getApplicationContext(),
                                        "User registered successfully",
                                        Toast.LENGTH_SHORT).show();

                                finish();

                                Intent i = new Intent(
                                        getApplicationContext(),
                                        ProfileActivity.class);

                                startActivity(i);

                            }else{

                                Toast.makeText(getApplicationContext(),
                                        "Registration failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}