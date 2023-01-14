package com.example.myhccc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class log_in extends AppCompatActivity {
    private EditText email, pass;
    private Button log_in;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private String work;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        log_in = findViewById(R.id.log_in);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email is empty", Toast.LENGTH_LONG).show();
                } else if (pass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "password is empty", Toast.LENGTH_LONG).show();
                } else if (email.getText().toString().equals("admin") && (pass.getText().toString().equals("admin"))) {
                    Toast.makeText(getApplicationContext(), "Welcome Admin ", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), admin_main.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Welcome All ", Toast.LENGTH_LONG).show();
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                    firebaseUser.getUid();
                                    databaseReference.child("users").child(firebaseUser.getUid()).child("work").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            work = snapshot.getValue(String.class);
                                            Toast.makeText(getApplicationContext(), work, Toast.LENGTH_LONG).show();
                                            if (work.equals("TECH")) {
                                                startActivity(new Intent(getApplicationContext(), tech_activity.class));
                                            } else if (work.equals("pharma")) {
                                                startActivity(new Intent(getApplicationContext(), pharma_main.class));
                                            }
                                            else if (work.equals("nurse")) {
                                                startActivity(new Intent(getApplicationContext(), NURSE_MAIN.class));
                                            }
                                            //else if (work.equals("doctor")) {
                                                //startActivity(new Intent(getApplicationContext(), .class));
                                        //    }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


                                }
                            });


                }


            }
        });


    }
}