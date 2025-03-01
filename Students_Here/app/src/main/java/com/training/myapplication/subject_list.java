package com.training.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.training.myapplication.Adapter.ItemAdapter;
import com.training.myapplication.DataModel.Item;

import java.util.ArrayList;
import java.util.List;

public class subject_list extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_subject_list);

        recyclerView = findViewById(R.id.recycle_sub);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Hindi"));
        itemList.add(new Item("Bengali"));
        itemList.add(new Item("Operating System"));
        itemList.add(new Item("Computer Network"));
        itemList.add(new Item("C Programing"));
        itemList.add(new Item("Python Programing"));
        itemList.add(new Item("Java Programing"));
        itemList.add(new Item("C++ Programing"));
        itemList.add(new Item("JS Programing"));
        itemList.add(new Item("Computer Architecture"));
        itemList.add(new Item("DBMS"));
        itemList.add(new Item("Control System"));
        itemList.add(new Item("Linux"));
        itemList.add(new Item("Artificial Intelligence"));
        itemList.add(new Item("Machine Learning"));

        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}