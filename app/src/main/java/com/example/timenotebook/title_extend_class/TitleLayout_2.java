package com.example.timenotebook.title_extend_class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.timenotebook.EditContentActivity;
import com.example.timenotebook.R;
import com.example.timenotebook.databases_about.NoteContentDataBaseHelper;

public class TitleLayout_2 extends LinearLayout {

    public TitleLayout_2(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.top_function_layout_2, this);



    }
}
