package com.example.timenotebook.title_extend_class;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.timenotebook.R;

public class TitleLayout_2 extends LinearLayout {

    public TitleLayout_2(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.top_function_layout_2, this);



    }
}
