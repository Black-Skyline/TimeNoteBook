package com.example.timenotebook.title_extend_class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.timenotebook.EditContentActivity;
import com.example.timenotebook.R;

public class TitleLayout_1 extends LinearLayout {

    public TitleLayout_1(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.top_function_layout_1, this);

        Button title_back = findViewById(R.id.top_back);

        title_back.setOnClickListener(view -> ((Activity) getContext()).finish());

    }
}



