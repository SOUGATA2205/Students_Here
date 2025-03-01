package com.training.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.training.myapplication.DatabaseHelper.DatabaseHelper;

public class ProfileActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText name, course, edtemail, etdphone, Year, cgpa;
    Button btnins, btnUpdate, btnDelete, btnView;
    int studentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        databaseHelper = new DatabaseHelper(this);

        name = findViewById(R.id.name);
        Year = findViewById(R.id.Year);
        cgpa = findViewById(R.id.cgpa);
        course = findViewById(R.id.course);
        edtemail = findViewById(R.id.edtemail);
        btnins = findViewById(R.id.btnins);
        etdphone = findViewById(R.id.etdphone);
        btnView = findViewById(R.id.btnView);
        btnDelete = findViewById(R.id.btndel);
        btnUpdate = findViewById(R.id.btnup);

        loadStudentData();

        btnins.setOnClickListener(view -> {
            if (studentId == -1) {
                boolean inserted = databaseHelper.insertStudent(
                        name.getText().toString(),
                        course.getText().toString(),
                        edtemail.getText().toString(),
                        etdphone.getText().toString(),
                        Year.getText().toString(),
                        cgpa.getText().toString()
                );
                if (inserted) {
                    showMessage(true, "Inserted");
                    loadStudentData();
                } else {
                    showMessage(false, "Insert");
                }
            } else {
                showMessage(false, "Student already exists!");
            }
        });

        btnUpdate.setOnClickListener(view -> {
            if (studentId != -1) {
                boolean updated = databaseHelper.updateStudent(studentId,
                        name.getText().toString(),
                        course.getText().toString(),
                        edtemail.getText().toString(),
                        etdphone.getText().toString(),
                        Year.getText().toString(),
                        cgpa.getText().toString()
                );
                showMessage(updated, "Updated");
            } else {
                showMessage(false, "No student to update!");
            }
        });

        btnDelete.setOnClickListener(view -> {
            if (studentId != -1) {
                boolean deleted = databaseHelper.deleteStudent(studentId);
                showMessage(deleted, "Deleted");
                if (deleted) {
                    clearFields();
                    studentId = -1;
                }
            } else {
                showMessage(false, "No student to delete!");
            }
        });

        btnView.setOnClickListener(v -> {
            if (studentId != -1) {
                Intent intent = new Intent(ProfileActivity.this, View_Activity.class);
                intent.putExtra("STUDENT_ID", studentId);
                Log.d("ProfileActivity", "Passing Student ID: " + studentId);
                startActivity(intent);
            } else {
                showMessage(false, "No student to view!");
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void clearFields() {
        name.setText("");
        cgpa.setText("");
        course.setText("");
        etdphone.setText("");
        edtemail.setText("");
        Year.setText("");
    }

    private void showMessage(boolean success, String action) {
        Toast.makeText(this, success ? action + " successfully!" : action + " failed!", Toast.LENGTH_LONG).show();
    }

    private void loadStudentData() {
        Cursor cursor = databaseHelper.getFirstStudent();
        if (cursor != null && cursor.moveToFirst()) {
            studentId = cursor.getInt(0);
            name.setText(cursor.getString(1));
            course.setText(cursor.getString(2));
            etdphone.setText(cursor.getString(3));
            edtemail.setText(cursor.getString(4));
            cgpa.setText(cursor.getString(5));
            Year.setText(cursor.getString(6));
            Log.d("ProfileActivity", "Student ID Loaded: " + studentId);
        } else {
            studentId = -1;
            Log.d("ProfileActivity", "No student found");
        }
        if (cursor != null) cursor.close();
    }

}

