package com.example.timenotebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.timenotebook.adapter.NoteDisplayAdapter;
import com.example.timenotebook.databases_about.GetTime;
import com.example.timenotebook.databases_about.NoteContentDataBaseHelper;
import com.example.timenotebook.listview_padding_method.Padding_Method;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private List<Padding_Method> noteList;
    private ListView listview;
    private NoteDisplayAdapter adapter;
    private List<Padding_Method> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        listview = findViewById(R.id.list_view);
        Button new_edit = findViewById(R.id.top_add);

        NoteContentDataBaseHelper dbhelper = new NoteContentDataBaseHelper(this);
        data = dbhelper.getAlist("all");
        adapter = new NoteDisplayAdapter(this, data);
        listview.setAdapter(adapter);


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Log.d("cao", "return ture");
                    if (result.getResultCode() == RESULT_OK) {
                        NoteContentDataBaseHelper dbHelper = new NoteContentDataBaseHelper(this);
                        Log.d("test5", "clear前成功" + data);
//                        dbHelper.Clear_Alist();
                        this.data.clear();
                        this.data = dbHelper.getAlist("all");
                        adapter = new NoteDisplayAdapter(this, this.data);
                        Log.d("cao", String.valueOf(this.data.size()));
                        Log.d("cao", String.valueOf(adapter.getCount()));
                        listview.setAdapter(adapter);
                        Log.d("test6", "adapter后成功");
                    } else {
                        Toast.makeText(MainActivity.this, "哦吼 加载失败  返回码为：" + result.getResultCode(), Toast.LENGTH_SHORT).show();

                    }
                });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new AlertDialog.Builder(MainActivity.this).setMessage("是否删除此项").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Padding_Method position_data = data.get(position);
                        Log.d("cao","d"+position_data.getListview_item_id());
                        dbhelper.Delete(position_data.getListview_item_id());
                        Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        data.remove(position);
                        adapter.notifyDataSetChanged();

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).create().show();
                return true;
            }
        });

        //ListView的点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Log.d("cao", "c1" + position);
                Padding_Method position_data = data.get(position);
                Log.d("cao", "c2" + position_data.getListview_item_id());
                Intent intent = new Intent(MainActivity.this, EditContentActivity.class);
                intent.putExtra("extra_data_state", "change");
                intent.putExtra("extra_data_id", position_data.getListview_item_id());
                intent.putExtra("extra_data_title", position_data.getListview_item_title());
                intent.putExtra("extra_data_content", position_data.getListview_item_content());
                Log.d("cao", "c3");
                launcher.launch(intent);
            }
        });
        new_edit.setOnClickListener(view -> {
            Intent intent = new Intent(new_edit.getContext(), EditContentActivity.class);
            intent.putExtra("extra_data_state", "new");
            intent.putExtra("extra_data_createTime", GetTime.getTime("OK"));
            launcher.launch(intent);
        });

    }


}