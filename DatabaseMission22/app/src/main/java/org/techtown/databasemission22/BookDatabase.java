package org.techtown.databasemission22;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookDatabase {
    private static final String TAG = "BookDatabase";
    private static String DATABASE_NAME = "book.db";
    public static final String TABLE_NAME = "BOOK_INFO";
    public static final int DATABASE_VERSION = 1;

    private Context context;
    private SQLiteDatabase db;
    private DatabaseHelper helper;

    public BookDatabase(Context context) {
        this.context = context;
    }

    // Singleton 객체 생성
    private static BookDatabase database;
    public static BookDatabase getInstance(Context context){
        if(database == null){
            database = new BookDatabase(context);
        }
        return  database;
    }

    public boolean Open(){
        println("opening database [" + DATABASE_NAME + "].");
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();

        return true;
    }

    public void close(){
        println("Closing database [" + DATABASE_NAME + "].");
        db.close();
        database = null;
    }

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(@Nullable Context context) {
            super(context, TABLE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            println("creating table [" + TABLE_NAME + "].");

            String DROP_SQL = " DROP TABLE IF EXISTS " + TABLE_NAME;
            try {
                println("DROP SQL 실행");
                db.execSQL(DROP_SQL);
            }catch (Exception e){
                Log.d(TAG, "Exception In DROP SQL");
            }

            String CREATE_TABLE_SQL = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    " NAME TEXT, " +
                    " AUTHOR TEXT, " +
                    " CONTENTS TEXT " +
                    ")";

            try {
                db.execSQL(CREATE_TABLE_SQL);
            } catch(Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }

            insertRecord(db, "Do it! 안드로이드 앱 프로그래밍", "정재곤", "안드로이드 기본서로 이지스퍼블리싱 출판사에서 출판했습니다.");
            insertRecord(db, "Programming Android", "Mednieks, Zigurd", "Oreilly Associates Inc에서 2011년 04월에 출판했습니다.");
            insertRecord(db, "센차터치 모바일 프로그래밍", "이병옥,최성민 공저", "에이콘출판사에서 2011년 10월에 출판했습니다.");
            insertRecord(db, "시작하세요! 안드로이드 게임 프로그래밍", "마리오 제흐너 저", "위키북스에서 2011년 09월에 출판했습니다.");
            insertRecord(db, "실전! 안드로이드 시스템 프로그래밍 완전정복", "박선호,오영환 공저", "DW Wave에서 2010년 10월에 출판했습니다.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { }

        public void insertRecord(SQLiteDatabase _db, String name, String author, String contents) {
            try {
                _db.execSQL( "insert into " + TABLE_NAME + "(NAME, AUTHOR, CONTENTS) values ('" + name + "', '" + author + "', '" + contents + "');" );
            } catch(Exception ex) {
                Log.e(TAG, "Exception in executing insert SQL.", ex);
            }
        }
    }

    public void insertRecord(String name, String author, String contents) {
        try {
            db.execSQL( "insert into " + TABLE_NAME +
                    "(NAME, AUTHOR, CONTENTS) values " +
                    "('" + name + "', '" + author + "', '" + contents + "');" );
        } catch(Exception ex) { Log.e(TAG, "Exception in executing insert SQL.", ex); }
    }

    public ArrayList<BookInfo> selectAll(){
        ArrayList<BookInfo> result = new ArrayList<BookInfo>();

        try {
            Cursor cursor = db.rawQuery("select NAME, AUTHOR, CONTENTS from " + TABLE_NAME, null);
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String name = cursor.getString(0);
                String author = cursor.getString(1);
                String contents = cursor.getString(2);

                BookInfo info = new BookInfo(name, author, contents);
                result.add(info);
                //cursor.colse() 닫으면 안됨!!!
            }

        } catch(Exception ex) {
            Log.e(TAG, "Exception in executing insert SQL.", ex);
        }
        return result;
    }

    private void println(String msg) {
        Log.d(TAG, msg);
    }

}
