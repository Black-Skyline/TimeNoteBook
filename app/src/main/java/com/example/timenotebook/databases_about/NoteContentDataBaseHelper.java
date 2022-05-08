package com.example.timenotebook.databases_about;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.timenotebook.listview_padding_method.Padding_Method;

import java.util.ArrayList;
import java.util.List;

public class NoteContentDataBaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_NOTE_TABLE = "create table Note ("
            + "id_number integer primary key autoincrement,"
            + "content longtext,"
            + "createTime text,"
            + "updateTime text,"
            + "notetitle text,"
            + "anchor long)";//记录最近一次同步时间

    public NoteContentDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public NoteContentDataBaseHelper(Context context){
        super(context,"MyNote.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE_TABLE);
//        Log.d("test","建表成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private int current_id_number;
    private String current_content;
    private String current_createTime;
    private String current_updateTime;
    private String current_notetitle;
    private long current_anchor;
    private List<Padding_Method> alist = new ArrayList<>();

    //添加数据
    public void AddData(String title_put_data, String content_put_data, String createTime_put_data,String updateTime_put_data) {
        if (title_put_data.isEmpty() && content_put_data.isEmpty()) {
        }else {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("notetitle", title_put_data);
            values.put("content", content_put_data);
            values.put("createTime", createTime_put_data);
            values.put("updateTime", updateTime_put_data);
            db.insert("Note", null, values);
            values.clear();
        }

    }

    //更新数据
    public void UpdateData(Padding_Method Median_value) {

        String title_put_data = Median_value.getListview_item_title();
        String content_put_data = Median_value.getListview_item_content();
        String updateTime_put_data = Median_value.getListview_item_update_time();

        if (title_put_data.isEmpty() && content_put_data.isEmpty()) {
            Delete(Median_value.getListview_item_id());
        }else{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("notetitle", title_put_data);
            values.put("content", content_put_data);
            values.put("updateTime", updateTime_put_data);
            db.update("Note", values, "id_number=?", new String[] {Median_value.getListview_item_id()});
            values.clear();
        }
    }

    //删除
    public boolean Delete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete("Note", "id_number= ? ", new String[]{id});
        return result > 0;
    }

    //查寻表数据
    @SuppressLint("Range")
    public void Query(String str) {

        //全表通查
        Cursor cursor = getWritableDatabase().query("Note", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                //用cursor对象遍历，并将查询结果储存
                switch (str) {
                    case "id_number": {
                        int id = cursor.getInt(cursor.getColumnIndex("id_number"));
                        setId_number(id);
                        break;
                    }
                    case "content": {
                        String content = cursor.getString(cursor.getColumnIndex("content"));
                        setContent(content);
                        break;
                    }
                    case "createTime": {
                        String createTime = cursor.getString(cursor.getColumnIndex("createTime"));
                        setCreateTime(createTime);
                        break;
                    }
                    case "updateTime": {
                        String updateTime = cursor.getString(cursor.getColumnIndex("updateTime"));
                        setUpdateTime(updateTime);
                        break;
                    }
                    case "notetitle": {
                        String notetitle = cursor.getString(cursor.getColumnIndex("notetitle"));
                        setNotetitle(notetitle);
                        break;
                    }
                    case "anchor": {
                        long anchor = cursor.getLong(cursor.getColumnIndex("anchor"));
                        setAnchor(anchor);
                        break;
                    }
                    case "all": {
                        int id = cursor.getInt(cursor.getColumnIndex("id_number"));
                        String notetitle = cursor.getString(cursor.getColumnIndex("notetitle"));
                        String content = cursor.getString(cursor.getColumnIndex("content"));
                        String createTime = cursor.getString(cursor.getColumnIndex("createTime"));
                        String updateTime = cursor.getString(cursor.getColumnIndex("updateTime"));
                        long anchor = cursor.getLong(cursor.getColumnIndex("anchor"));
                        build_list(id, notetitle, content, createTime, updateTime);
                        break;
                    }
                }

            }while (cursor.moveToNext()) ;
            cursor.close();
        }
    }
    public List<Padding_Method> getAlist(String str) {
        Query(str);
        return alist;
    }

    public void setId_number(int x) {
        this.current_id_number = x;
    }

    public void setContent(String x) {
        this.current_content = x;
    }

    public void setCreateTime(String x) {
        this.current_createTime = x;
    }

    public void setUpdateTime(String x) {
        this.current_updateTime = x;
    }

    public void setNotetitle(String x) {
        this.current_notetitle = x;
    }

    public void setAnchor(long x) {
        this.current_anchor = x;
    }

    public void build_list(int id, String title, String content, String create_time, String update_time) {
        alist.add(new Padding_Method(id, title, content, create_time, update_time));
    }
}
