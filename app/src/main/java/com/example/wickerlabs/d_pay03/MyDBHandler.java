package com.example.wickerlabs.d_pay03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper{



    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Dpay.db";
    private static final String TABLE_CARDS = "cards";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CARDNAME = "kuponi";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE "+ TABLE_CARDS+ " ("
                + COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CARDNAME+ " varchar )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CARDS);
        onCreate(db);
    }


        // Adding a Row in a DataBase
    public void addCard(String detail){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CARDNAME, detail);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CARDS, null, values);
        db.close();
    }
        // Deleting a Row in DataBase
    public void deleteCard(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_CARDS, null, null);
        db.close();
        //db.execSQL("DELETE FROM "+ TABLE_CARDS+ " WHERE "+ COLUMN_CARDNAME);
        System.out.println("Deleting done");
    }

    public String databseToString(){
        List list = new ArrayList<>();
        String dbString =" ";
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " +TABLE_CARDS+ "  WHERE 1";
            // Cursor point to a location in results
        Cursor c = db.rawQuery(query, null);
        //c.moveToFirst();

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            Log.i("database", "adding to database");
            if (c.getString(c.getColumnIndex("kuponi"))!= null){
                dbString += c.getString(c.getColumnIndex("kuponi"));
                dbString += "\n";
               // list.add(dbString);
            }
        }

        db.close();
        System.out.println(dbString);
        return dbString ;
    }


   /* public List getData(){

        List list = new ArrayList<>();
        SQLiteDatabase db= getWritableDatabase();
        String query = "SELECT * FROM " +TABLE_CARDS+ "  WHERE 1";
        Cursor cursor= db.rawQuery(query,null);
        while (cursor.moveToFirst()){

            int index0=cursor.getColumnIndex(UID);

            int index1=cursor.getColumnIndex(helper.KEY_NAME);
            int index2=cursor.getColumnIndex(helper.KEY_VALUE);
            int index3=cursor.getColumnIndex(helper.KEY_FORMAT);
            int index4=cursor.getColumnIndex(helper.KEY_COUNTRY);

            int cid = cursor.getInt(index0);
            String name = cursor.getString(index1);
            String value = cursor.getString(index2);
            String format = cursor.getString(index3);
            String country = cursor.getString(index4);
            Cards cards = new Cards(cid,name,value,format,country);

            list.add(cards);

        }

        return list;

    } */

    public void getCardsName() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;




       try{

            cursor =db.rawQuery("SELECT kuponi FROM cards WHERE 1", null);

            if(cursor.getCount() >= 0){

              /*  KuponiTab.arrayList = new ArrayList<String>();
                while(cursor.moveToNext()){
                    String uname = cursor.getString(cursor.getColumnIndex("kuponi"));
                    KuponiTab.arrayList.add(uname);
                   // KuponiTab.arrayList.
                    KuponiTab.recyclerAdapter.notifyDataSetChanged();

                }   */

                KuponiTab.arrayList = new ArrayList<String>();
                for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                //for(){

               // while(cursor.moveToNext()){

                String empName = cursor.getString(cursor.getColumnIndex("kuponi"));
                    KuponiTab.arrayList.add(empName);
                    KuponiTab.recyclerAdapter.notifyDataSetChanged();
                }
            }
           Log.i("Card total ", "Ziko "+cursor.getCount());

            //return empName;
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            cursor.close();
        }
    }
}
