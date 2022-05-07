package com.example.timenotebook;

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

        NoteContentDataBaseHelper dbhelper = new NoteContentDataBaseHelper(this, "MyNote.db", null, 1);
        data = dbhelper.getAlist("all");
        adapter = new NoteDisplayAdapter(this, data);
        listview.setAdapter(adapter);


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        NoteContentDataBaseHelper dbHelper = new NoteContentDataBaseHelper(this, "MyNote.db", null, 1);
                        Log.d("test5", "clear前成功" + data);

                        Log.d("test6", "adapter后成功");
                        dbHelper.Clear_Alist();
                        List<Padding_Method> updated_data = dbHelper.getAlist("all");
                        adapter.notifyDataSetChanged();
                        adapter = new NoteDisplayAdapter(this, updated_data);
                        listview.setAdapter(adapter);
                        Log.d("test6", "adapter后成功");
                    } else {
                        Toast.makeText(MainActivity.this, "哦吼 加载失败  返回码为：" + result.getResultCode(), Toast.LENGTH_SHORT).show();

                    }
                });


        //ListView的点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Padding_Method position_data = data.get(position);
                Intent intent = new Intent(MainActivity.this, EditContentActivity.class);
                intent.putExtra("extra_bean", position_data);
                intent.putExtra("extra_data_state", "change");
                intent.putExtra("extra_data_title", position_data.getListview_item_title());
                intent.putExtra("extra_data_content", position_data.getListview_item_content());
                startActivity(intent);
            }
        });
//        listview.setOnItemClickListener((adapterView, view, final i, l) -> {
//            Padding_Method position_data = data.get(i);
//            Intent intent = new Intent(this, EditContentActivity.class);
//            intent.putExtra("extra_data_state","change");
//            intent.putExtra("extra_data_title",position_data.getListview_item_title());
//            intent.putExtra("extra_data_content",position_data.getListview_item_content());
//            startActivity(intent);
//        });

        new_edit.setOnClickListener(view -> {
            Intent intent = new Intent(new_edit.getContext(), EditContentActivity.class);
            intent.putExtra("extra_data_state", "new");
            intent.putExtra("extra_data_createTime", GetTime.getTime("OK"));
            launcher.launch(intent);
        });

    }


}