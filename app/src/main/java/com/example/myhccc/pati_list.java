package com.example.myhccc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class pati_list extends AppCompatActivity {
    private EditText nationalnumber;
    private ImageButton bttsearch;
    private RecyclerView searchview;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;


    public pati_list() {
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pati_list);

        nationalnumber = findViewById(R.id.nationalnumber);
        bttsearch = findViewById(R.id.bttsearch);
        //searchview=findViewById(R.id.searchview);
        /////
        ArrayList<String> alldoctors;
        alldoctors = new ArrayList<String>();
        /////
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference.child("all_pat").child("national number").child("nationalnumber").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot sample : snapshot.getChildren()) {
                    String pat=sample.getValue(String.class);
                    alldoctors.add(pat);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, alldoctors);

                arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            }
            

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}