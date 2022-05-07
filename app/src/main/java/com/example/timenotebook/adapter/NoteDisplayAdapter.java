package com.example.timenotebook.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.timenotebook.R;
import com.example.timenotebook.listview_padding_method.Padding_Method;

import java.util.ArrayList;
import java.util.List;

public class NoteDisplayAdapter extends BaseAdapter {
    private List<Padding_Method> data;
    private Context context;
    private LayoutInflater inflater;

    public NoteDisplayAdapter(Context context, List<Padding_Method> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
//        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate (R.layout.listview_item_layout, null);
            convertView = inflater.inflate(R.layout.listview_item_layout, null);
        }
        Padding_Method Note = data.get(position);
        TextView id = convertView.findViewById(R.id.listview_id_number);
        TextView title = convertView.findViewById(R.id.listview_text_title);
        TextView content = convertView.findViewById(R.id.listview_text_content);
        TextView update_time = convertView.findViewById(R.id.listview_update_time);
        id.setText(Note.getListview_item_id());
        title.setText(Note.getListview_item_title());
        content.setText(Note.getListview_item_content());
        update_time.setText(Note.getListview_item_update_time());
        return convertView;
    }
}
