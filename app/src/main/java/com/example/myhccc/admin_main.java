package com.example.myhccc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class admin_main extends AppCompatActivity {

    private Spinner user_type;
    private Button start_sign_up,log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        user_type = findViewById(R.id.user_type);
        start_sign_up = findViewById(R.id.start_sign_up);
        log_out = findViewById(R.id.log_out);


//////spinner
        String[] users1 = {"select user type", "pharmacist", "Doctor", "Nurse", "Technician"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, users1);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        user_type.setAdapter(arrayAdapter);
//////////
        start_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_type.getSelectedItem() == "select user type") {
                    Toast.makeText(getApplicationContext(), "Please select type", Toast.LENGTH_SHORT).show();
                } else if (user_type.getSelectedItem() == "Doctor") {
                    startActivity(new Intent(getApplicationContext(), signup_doctor.class));
                    Toast.makeText(getApplicationContext(), "Doctor Sign up", Toast.LENGTH_SHORT).show();

                }else if (user_type.getSelectedItem() == "pharmacist") {
                    startActivity(new Intent(getApplicationContext(), pharma_signup.class));
                    Toast.makeText(getApplicationContext(), "pharmacist Sign up", Toast.LENGTH_SHORT).show();

                }else if (user_type.getSelectedItem() == "Nurse") {
                    startActivity(new Intent(getApplicationContext(), nurse_signup.class));
                    Toast.makeText(getApplicationContext(), "Nurse Sign up", Toast.LENGTH_SHORT).show();

                }else if (user_type.getSelectedItem() == "Technician") {
                    startActivity(new Intent(getApplicationContext(), tech_signup.class));
                    Toast.makeText(getApplicationContext(), "Technician Sign up", Toast.LENGTH_SHORT).show();

                }
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),log_in.class));
                finish();
            }
        });


    }
}