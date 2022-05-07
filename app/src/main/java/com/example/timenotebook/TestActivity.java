package com.example.timenotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestActivity extends AppCompatActivity {
    private String[] data = {"item1","item2","item1","item1","item1","item1","item1","item1","item1","item1","item1","item1","item1","item1","item1","item1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_item_layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TestActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);

    }
}