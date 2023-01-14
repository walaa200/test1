package com.example.myhccc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class add_med extends AppCompatActivity {
    private EditText med_name, how_to;
    private Button add;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);

        med_name=findViewById(R.id.med_name);
        how_to=findViewById(R.id.how_to);

        add=findViewById(R.id.add);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date= Calendar.getInstance().getTime();

                databaseReference.child("med").child(med_name.getText().toString()).child("med_name").setValue(med_name.getText().toString());
                databaseReference.child("med").child(med_name.getText().toString()).child("how_to").setValue(how_to.getText().toString());
                databaseReference.child("med").child(med_name.getText().toString()).child("time").setValue(date.toString());

                Toast.makeText(getApplicationContext(), "Med added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}