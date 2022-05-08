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

import java.util.List;

public class NoteDisplayAdapter extends BaseAdapter {
    private List<Padding_Method> data;
    private Context context;
    private String tip_createTime;
    private String tip_updateTime;

    public NoteDisplayAdapter(Context context, List<Padding_Method> data) {
        this.data = data;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_layout, null);
        }
        Padding_Method Note = data.get(position);

        //绑定
        TextView title = convertView.findViewById(R.id.listview_text_title);
        TextView content = convertView.findViewById(R.id.listview_text_content);
        TextView crate_time = convertView.findViewById(R.id.listview_crate_time);
        TextView update_time = convertView.findViewById(R.id.listview_update_time);

        //填充对应内容
        tip_createTime = "创建于" + Note.getListview_item_create_time();
        tip_updateTime = "更新于" + Note.getListview_item_update_time();

        if (Note.getListview_item_title().isEmpty()) {
        } else {
            title.setText(Note.getListview_item_title());
        }
        if (Note.getListview_item_content().isEmpty()) {
        } else {
            content.setText(Note.getListview_item_content());
        }
        crate_time.setText(tip_createTime);
        update_time.setText(tip_updateTime);

        return convertView;
    }
}
