package com.training.myapplication;

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
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn_login;
    EditText edt_email , edt_pass;
    TextView acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt_email=findViewById(R.id.edtemail);
        edt_pass= findViewById(R.id.edtpass);
        btn_login= findViewById(R.id.btnlogin);
        acc = findViewById(R.id.tag_accessibility_clickable_spans);

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String saveName = sharedPreferences.getString("Name", "");
        String savePass = sharedPreferences.getString ("Password", "");
        String saveEmail = sharedPreferences.getString("Email","");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString();
                String password = edt_pass.getText().toString();

                if (email.equals(saveEmail) && password.equals(savePass)){
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, dasboard.class));
                }
                else {
                   Toast.makeText(MainActivity.this, "Invalid Password or Username!", Toast.LENGTH_LONG).show();
                }
            }
        });

        acc.setClickable(true);
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationPage.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}