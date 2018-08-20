package com.example.heping.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {

    private String[] fruitNames= {"apple", "pear", "grape", "orange"};
    private ListView fruitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fruitList = findViewById(R.id.fruitList);
        ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, fruitNames);
        this.fruitList.setAdapter(adapter);
    }
}
