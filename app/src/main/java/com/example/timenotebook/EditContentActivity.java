package com.example.timenotebook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.timenotebook.databases_about.GetTime;
import com.example.timenotebook.databases_about.NoteContentDataBaseHelper;
import com.example.timenotebook.listview_padding_method.Padding_Method;

public class EditContentActivity extends AppCompatActivity {

    private NoteContentDataBaseHelper dbHelper;

    //当前的临时变量
    private String title_put_data;
    private String content_put_data;
    private String createTime_put_data;
    private String updateTime_put_data;
    private String state_value;
    private Intent intent = new Intent();
    private String current_id;
/*
    未完成的菜单
*/
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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

        //绑定
        EditText title_put = findViewById(R.id.edittext_item_title);
        EditText content_put = findViewById(R.id.edittext_item_content);
        TextView text_top_title = findViewById(R.id.text_top_title);

        Intent get_intent = getIntent();
        state_value = get_intent.getStringExtra("extra_data_state");

        //获取对应listview的item所属的id、title、content
        if (state_value.equals("change")) {
            title_put.setText(get_intent.getStringExtra("extra_data_title"));
            content_put.setText(get_intent.getStringExtra("extra_data_content"));
            current_id = get_intent.getStringExtra("extra_data_id");
            if (get_intent.getStringExtra("extra_data_title").isEmpty()) {
                text_top_title.setText("无标题");
            } else {
                text_top_title.setText(get_intent.getStringExtra("extra_data_title"));
            }

        }
        dbHelper = new NoteContentDataBaseHelper(this);

        //返回自动保存文本功能
        Button title_back = findViewById(R.id.top_back);
        title_back.setOnClickListener(view -> {
            title_put_data = title_put.getText().toString();
            content_put_data = content_put.getText().toString();
            updateTime_put_data = GetTime.getTime("NO");
            if (state_value.equals("change")) {
                //此情況为修改
                Padding_Method bean = new Padding_Method(Integer.parseInt(current_id), title_put_data, content_put_data, updateTime_put_data);
                dbHelper.UpdateData(bean);
            } else {
                //此情况为新建
                createTime_put_data = get_intent.getStringExtra("extra_data_createTime");
                dbHelper.AddData(title_put_data, content_put_data, createTime_put_data, updateTime_put_data);
            }

            setResult(RESULT_OK, intent);
            finish();
        });

        //点击按钮删除功能
        Button title_delete = findViewById(R.id.delete_note);
        title_delete.setOnClickListener(view -> {
            if (state_value.equals("change")) {
                //此情況为修改时点击
                new AlertDialog.Builder(EditContentActivity.this).setMessage("是否删除此项").setPositiveButton("确定", (dialogInterface, i) -> {
                    if (dbHelper.Delete(current_id)) {
                        Toast.makeText(EditContentActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditContentActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                    }

                    setResult(RESULT_OK, intent);
                    finish();
                }).setNegativeButton("取消", (dialogInterface, i) -> {
                }).create().show();

            } else {
                //此情况为新建时点击
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}