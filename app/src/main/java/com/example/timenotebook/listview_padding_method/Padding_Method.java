package com.example.timenotebook.listview_padding_method;

import java.io.Serializable;

public class Padding_Method implements Serializable {
    private String listview_item_id;
    private String listview_item_title;
    private String listview_item_content;
    private String listview_item_update_time;
    private String listview_item_create_time;


    public Padding_Method(int id, String title, String content, String create_time,String update_time) {
        listview_item_id = String.valueOf(id);
        listview_item_title = title;
        listview_item_content = content;
        listview_item_create_time = create_time;
        listview_item_update_time = update_time;
    }

    public Padding_Method(int id, String title, String content, String update_time) {
        listview_item_id = String.valueOf(id);
        listview_item_title = title;
        listview_item_content = content;
        listview_item_update_time = update_time;
    }

    public String getListview_item_id() {
        return listview_item_id;
    }

    public void setListview_item_id(int id){
        listview_item_id = String.valueOf(id);
    }

    public String getListview_item_title() {
        return listview_item_title;
    }

    public void setListview_item_title(String title){
        listview_item_title = title;
    }

    public String getListview_item_content() {
        return listview_item_content;
    }

    public void setListview_item_content(String content){
        listview_item_content = content;
    }

    public String getListview_item_create_time() {
        return listview_item_create_time;
    }

    public void setListview_item_create_time(String create_time){
        listview_item_create_time = create_time;
    }
    public String getListview_item_update_time() {
        return listview_item_update_time;
    }

    public void setListview_item_update_time(String update_time){
        listview_item_update_time = update_time;
    }

}
