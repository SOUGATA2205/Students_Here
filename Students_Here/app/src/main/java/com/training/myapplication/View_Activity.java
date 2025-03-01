package com.training.myapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.training.myapplication.DatabaseHelper.DatabaseHelper;

public class View_Activity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    TextView vName, vCourse, vEmail, vPhone, vYear, vCg;
    String studentId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);

        dbHelper = new DatabaseHelper(this);
        vName = findViewById(R.id.vName);
        vCourse = findViewById(R.id.vCourse);
        vEmail = findViewById(R.id.vEmail);
        vPhone = findViewById(R.id.vPhone);
        vYear = findViewById(R.id.year);
        vCg = findViewById(R.id.cgpa);


        if (getIntent().hasExtra("STUDENT_ID")) {
            studentId = String.valueOf(getIntent().getIntExtra("STUDENT_ID", -1));
            Log.d("ViewActivity", "Received Student ID: " + studentId); // Debug log
        } else {
            Toast.makeText(this, "Error: No student ID received!", Toast.LENGTH_LONG).show();
            Log.e("ViewActivity", "No student ID received!");
            return;
        }

        displayStudentData();
    }


    @SuppressLint("Range")
    private void displayStudentData() {
        Cursor cursor = dbHelper.getStudentById(studentId);
        if (cursor != null && cursor.moveToFirst()) {
            vName.setText("Name: " + cursor.getString(cursor.getColumnIndex("name")));
            vCourse.setText("Course: " + cursor.getString(cursor.getColumnIndex("course")));
            vEmail.setText("Email: " + cursor.getString(cursor.getColumnIndex("email")));
            vPhone.setText("Phone: " + cursor.getString(cursor.getColumnIndex("phone")));
            vYear.setText("Year: " + cursor.getString(cursor.getColumnIndex("year")));
            vCg.setText("CGPA: " + cursor.getString(cursor.getColumnIndex("cgpa")));
        } else {
            Toast.makeText(this, "No student data found!", Toast.LENGTH_LONG).show();
        }
        if (cursor != null) cursor.close();
    }
}
