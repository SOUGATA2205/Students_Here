package com.training.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationPage extends AppCompatActivity {
TextView haveAc;
EditText edtPassword, edtName, edtEmail;
Button btnreg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_page);

        haveAc = findViewById(R.id.haveAcc);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        btnreg = findViewById(R.id.btnreg);

        haveAc.setClickable(true);
        haveAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationPage.this, MainActivity.class));
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edtEmail.getText().toString();
                String Name = edtName.getText().toString();
                String password = edtPassword.getText().toString();

                if(Name.isEmpty() || Email.isEmpty() || password.isEmpty()){
                    Toast.makeText(RegistrationPage.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }else {
                    SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Name", Name);
                    editor.putString("Email", Email);
                    editor.putString("Password", password);
                    editor.apply();

                    Toast.makeText(RegistrationPage.this, "Registred Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegistrationPage.this, MainActivity.class));
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}