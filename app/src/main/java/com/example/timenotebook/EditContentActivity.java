package com.example.timenotebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timenotebook.databases_about.GetTime;
import com.example.timenotebook.databases_about.NoteContentDataBaseHelper;
import com.example.timenotebook.listview_padding_method.Padding_Method;

public class EditContentActivity extends AppCompatActivity {

    private NoteContentDataBaseHelper dbHelper;
    private String title_put_data;
    private String content_put_data;
    private String createTime_put_data;
    private String updateTime_put_data;
    private String state_value;

/*
    未完成的菜单
 */

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        //查表数据
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor cursor = db.query("Note", null, null, null, null, null, null);
//        int id_number_this = 0;
//        if (cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range") int id_number = cursor.getInt(cursor.getColumnIndex("id_number"));
//                id_number_this = id_number;
//            } while (cursor.moveToNext());
//        }
//
//        switch (item.getItemId()) {
//            case R.id.more_item:
//            case R.id.setting_item:
//            case R.id.words_number_item:
//                break;
//            case R.id.delete_item:
//                db.delete("Note", "id_number = ?", new String[]{"id_number_this"});
//                break;
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.top_function_menu, menu);
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_content_layout);

        EditText title_put = findViewById(R.id.edittext_item_title);
        EditText content_put = findViewById(R.id.edittext_item_content);

        Intent get_intent = getIntent();
        state_value = get_intent.getStringExtra("extra_data_state");


        Log.d("test1", "是" + state_value);


        if (state_value.equals("change")) {
            title_put.setText(get_intent.getStringExtra("extra_data_title"));
            content_put.setText(get_intent.getStringExtra("extra_data_content"));
        } else {

        }
        dbHelper = new NoteContentDataBaseHelper(this, "MyNote.db", null, 1);

        //返回自动保存文本功能
        Button title_back = findViewById(R.id.top_back);
        title_back.setOnClickListener(view -> {

            //判断是新建还是修改
            if (state_value.equals("change")) {

            } else {
                createTime_put_data = get_intent.getStringExtra("extra_data_createTime");
            }

            //排除连续两次保存相同的文本的情况
            if (title_put.getText().toString().equals(title_put_data) && content_put.getText().toString().equals(content_put_data)) {
//                Toast.makeText(this, "已保存成功", Toast.LENGTH_SHORT).show();
            }
            //排除修改时文本未更改的情况
            else if (title_put.getText().toString().equals(get_intent.getStringExtra("extra_data_title")) && content_put.getText().toString().equals(get_intent.getStringExtra("extra_data_content"))) {
//                Toast.makeText(this, "已保存成功", Toast.LENGTH_SHORT).show();
            } else {
                //Log.d("test","title_put_data是"+title_put_data);
                //记录文本标题和内容数据
                title_put_data = title_put.getText().toString();
                content_put_data = content_put.getText().toString();
                updateTime_put_data = GetTime.getTime("NO");

                String str = null;

                if (state_value.equals("new")) {
                    //尝试添加 数据到数据库里并获取添加结果
                    str = dbHelper.AddData(title_put_data, content_put_data, createTime_put_data, updateTime_put_data);
                } else if (state_value.equals("change")) {
                    //尝试更改 数据到数据库里并获取更改结果
                    Padding_Method Median_value = get_intent.getParcelableExtra("extra_bean");
                    str = dbHelper.UpdateData(Median_value);
                    Toast.makeText(this, "是" + Median_value.getListview_item_content(), Toast.LENGTH_SHORT).show();

                }
                if (title_put_data.isEmpty() && content_put_data.isEmpty()) {
                    //反馈取消
                    Intent intent = new Intent();
                    intent.putExtra("data_return", str);
                    setResult(RESULT_CANCELED, intent);
                } else {
                    //反馈确认
                    Intent intent = new Intent();
                    intent.putExtra("data_return", str);
                    setResult(RESULT_OK, intent);
                    Log.d("test1", "返回码" + RESULT_OK + "成功");
                }
            }
            finish();
        });

        //手动保存文本
        Button title_save = findViewById(R.id.save_note);
        title_save.setOnClickListener(view -> {

            //判断是新建还是修改
            if (state_value.equals("change")) {

            } else {
                createTime_put_data = get_intent.getStringExtra("extra_data_createTime");
            }

            //排除连续两次保存相同的文本的情况
            if (title_put.getText().toString().equals(title_put_data) && content_put.getText().toString().equals(content_put_data)) {
                Toast.makeText(this, "已保存成功", Toast.LENGTH_SHORT).show();
            }
            //排除修改时文本未更改的情况
            else if (title_put.getText().toString().equals(get_intent.getStringExtra("extra_data_title")) && content_put.getText().toString().equals(get_intent.getStringExtra("extra_data_content"))) {
                Toast.makeText(this, "已保存成功", Toast.LENGTH_SHORT).show();
            } else {
                //Log.d("test","title_put_data是"+title_put_data);
                //记录文本标题和内容数据
                title_put_data = title_put.getText().toString();
                content_put_data = content_put.getText().toString();
                updateTime_put_data = GetTime.getTime("NO");

                Log.d("test2", "标题是" + title_put.getText().toString());
                Log.d("test3", "内容是" + content_put.getText().toString());
                Log.d("test4", "时间是" + updateTime_put_data);


                String str = null;

                if (state_value.equals("new")) {
                    //尝试添加 数据到数据库里并获取添加结果
                    str = dbHelper.AddData(title_put_data, content_put_data, createTime_put_data, updateTime_put_data);
                } else if (state_value.equals("change")) {
                    //尝试更改 数据到数据库里并获取更改结果
                    Padding_Method Median_value = (Padding_Method) get_intent.getSerializableExtra("extra_bean");
                    str = dbHelper.UpdateData(Median_value);
                }
                if (title_put_data.isEmpty() && content_put_data.isEmpty()) {
                    //提示添加结果
                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                    //反馈取消
                    Intent intent = new Intent();
//                    intent.putExtra("data_return", str);
                    setResult(RESULT_CANCELED, intent);
                } else {
                    //提示添加结果
                    Log.d("tag", title_put_data + "你tnnd" + content_put_data);
                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                    //反馈确认
                    Intent intent = new Intent();
//                    intent.putExtra("data_return", str);
                    setResult(RESULT_OK, intent);
                    Log.d("test", "返回码" + RESULT_OK + "成功");

                }
                finish();
            }

        });
    }
}