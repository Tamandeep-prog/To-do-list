package com.exam.todolist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class NoteHandler extends DatabaseHelper{



    public NoteHandler(Context context) {
        super(context);
    }


    public boolean create(note nte)
    {
        ContentValues values =new ContentValues();

        values.put("title", nte.getTitle());
        values.put("description", nte.getDescription());

        SQLiteDatabase db= this.getWritableDatabase();
        boolean isSuccessfull=db.insert("Note", null, values)>0;
        db.close();

        return isSuccessfull;

    }

    public ArrayList<note> readNotes(){
        ArrayList<note> notes = new ArrayList<note>();

        String sqlQuery = " SELECT * FROM Note ORDER BY id ASC";
        SQLiteDatabase db= this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery, null);

        if(cursor.moveToFirst())
        {
            do{
                @SuppressLint("Range") int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                note nte= new note(title,description);
                nte.setId(id);
                notes.add(nte);

            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return notes;

    }

    public note readSingleNote(int id){

        note nte=null;
        String sqlQuery = "SELECT * FROM Note where id= "+ id;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);

        if(cursor.moveToFirst()){
            @SuppressLint("Range") int noteId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

            nte=new note(title, description);
            nte.setId(noteId);
        }
        cursor.close();
        db.close();
        return nte;

    }

    public boolean update(note nte){
        ContentValues values = new ContentValues();
        values.put("title", nte.getTitle());
        values.put("description", nte.getDescription());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isSuccess = db.update("Note", values, "id='"+nte.getId()+"'", null)>0;
        db.close();
        return isSuccess;

    }
    public boolean delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isSuccess = db.delete("Note",  "id='"+id+"'", null)>0;
        db.close();
        return isSuccess;

    }


}
