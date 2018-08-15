package com.example.asus.loginactivity;

import android.content.Intent;
import android.database.Cursor;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TextModel {
    int id;
    String text;

    public TextModel(int id, String text){
        this.id=id;
        this.text=text;
    }
    public TextModel(String text){
        this.text=text;
    }

    public int getId() {return id;}
    public void setId(Integer id){this.id=id;}
    public String getText(){return text;}
    public void setText(String text){this.text=text;}
    public static ArrayList<TextModel> cursorToArray (Cursor input){
        ArrayList<TextModel> output=new ArrayList<>();
        while(input.moveToNext()){
            output.add(new TextModel(input.getInt(0),input.getString(1)));
        }
        return output;
    }
    @Override
    public String toString(){return String.valueOf(id) + " - " + text;}
}
