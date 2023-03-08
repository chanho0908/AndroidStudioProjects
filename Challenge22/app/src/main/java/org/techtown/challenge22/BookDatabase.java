package org.techtown.challenge22;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookDatabase {
    public static final String TAG="BookDatabase";
    public static final String DATABASE_NAME = "book.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "BOOK_INFO";
    private static BookDatabase database;

    private DataBaseHelper helper;
    private SQLiteDatabase db;
    private Context context;

    //Context 생성자
    private BookDatabase(Context context) {
        this.context = context;
    }

    // Singleton 객체, 현재 앱의 데이터베이스 인스턴스 변수가 한번만 생성;
    public static BookDatabase getInstance(Context context){
        if(database == null){
            database = new BookDatabase(context);
        }
        return database;
    }

    //
    public boolean open(){
        println("opening database [" + DATABASE_NAME + "].");
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();

        return true;
    }

    public void close() {
        println("closing database [" + DATABASE_NAME + "].");
        db.close();
        database = null;
    }

    public Cursor rawQuery(String SQL){
        println("\nexecuteQuery called.\n");

        Cursor cursor = null;
        try{
            cursor = db.rawQuery(SQL, null);
            println("cursor count : " + cursor.getCount());
        }catch (Exception e){e.printStackTrace();
        }

        return cursor;
    }

    public boolean execSQL(String SQL){
        println("\nexecute called.\n");

        try {
            db.execSQL(SQL);
        }catch (Exception e) {e.printStackTrace();}

        return true;
    }

    private class DataBaseHelper extends SQLiteOpenHelper{

        public DataBaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            println("creating table [" + TABLE_NAME + "].");

            // Drop existing Table
            String Drop_SQL = " DROP TABLE IF EXISTS " + TABLE_NAME;

            try{
                db.execSQL(Drop_SQL);
            }catch (Exception e) {
                Log.e(TAG, "Exception in DROP_SQL", e);
            }

            // create table
            String CREATE_SQL = "create table " + TABLE_NAME + "("
                    + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  NAME TEXT, "
                    + "  AUTHOR TEXT, "
                    + "  CONTENTS TEXT, "
                    + "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";

            try {
                db.execSQL(CREATE_SQL);
            }catch (Exception e){
                Log.e(TAG, "Exception in CREATE_SQL", e);
            }


            insertRecord(db, "Do it! 안드로이드 앱 프로그래밍", "정재곤", "안드로이드 기본서로 이지스퍼블리싱 출판사에서 출판했습니다.");
            insertRecord(db, "Programming Android", "Mednieks, Zigurd", "Oreilly Associates Inc에서 2011년 04월에 출판했습니다.");
            insertRecord(db, "센차터치 모바일 프로그래밍", "이병옥,최성민 공저", "에이콘출판사에서 2011년 10월에 출판했습니다.");
            insertRecord(db, "시작하세요! 안드로이드 게임 프로그래밍", "마리오 제흐너 저", "위키북스에서 2011년 09월에 출판했습니다.");
            insertRecord(db, "실전! 안드로이드 시스템 프로그래밍 완전정복", "박선호,오영환 공저", "DW Wave에서 2010년 10월에 출판했습니다.");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    private void insertRecord(SQLiteDatabase db, String name, String author, String contents) {
        try {
            db.execSQL( "insert into " + TABLE_NAME
                    + "(NAME, AUTHOR, CONTENTS) " +
                    "values ('" + name + "', '" + author + "', '" + contents + "');" );
        } catch(Exception ex) {
            Log.e(TAG, "Exception in executing insert SQL.", ex);
        }
    }

    public void insertRecord( String name, String author, String contents) {
        String INSERT_SQL = "INSERT INTO " + TABLE_NAME
                + "(NAME, AUTHOR, CONTENTS) " +
                "VALUES ('" + name + "', '" + author + "',  '" + contents + "');";
        try {
            db.execSQL(INSERT_SQL);
        } catch (Exception e) {
            Log.e(TAG, "Exception in executing insert SQL.", e);
        }
    }


    private void println(String msg) {
        Log.d(TAG, msg);
    }

    public ArrayList<BookInfo> selectAll() {
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
            }

        } catch(Exception ex) {
            Log.e(TAG, "Exception in executing insert SQL.", ex);
        }

        return result;
    }
    //리사이클러뷰 리스트에 sqlite 값 뿌려주기
    /*
     * 리사이클러뷰의 리스트에 db테이블에 있는 모든 쿼리를 리스트에 담아 어뎁터의 리스트로 반환하면 된다.  (->setitems()함수)
     * 그래서 어뎁터의 리스트로 반환하려면, Persondatabase 클래스에 public ArrayList<Person>selectAll() 반환함수를만들어놓았다.
     */
}