package org.techtown.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    TextView textView;

    String tableName;

    SQLiteDatabase database;
    DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRecord();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executequery();

            }
        });
    }

    private void createDatabase(String databaseName) {

        try {
            helper = new DataBaseHelper(this);
            database = helper.getWritableDatabase();

            println("데이터 베이스 생성됨 : " + databaseName);
        }catch (Exception e){e.printStackTrace();
        }
    }

    private void createTable(String tableName){
        println("createTable 호출");
        if(database == null){
            println("데이터베이스를 먼저 열어주세요");
            return;
        }else {
            try {
                database.execSQL("create table if not exists " + tableName +
                        "(" + " _id integer PRIMARY KEY autoincrement, " + " name text, " + " age integer, " + " mobile text)");

                println("테이블 생성됨" + tableName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void insertRecord() {
        println("insertRecord 호출");

        if(database == null){
            println("데이터베이스를 먼저 열어주세요");
            return;
        }

        String tableName = editText2.getText().toString();
        if(tableName == null){
            println("테이블을 먼저 생성하세요");
        }
        try {
            database.execSQL("insert into " + tableName
                    + "(name, age, mobile) "
                    + " values "
                    + "('John', 20, '010-1000-1000')");

            println("레코드 추가함");
        }catch (Exception e) {e.printStackTrace();}

    }


    private void executequery() {
        println("executequery 호출");

        String sql = "select _id, name, age, mobile from customer ";
        String tableName = editText2.getText().toString();

        try {
            if(tableName == null){
                println("테이블 이름을 입력하세요");
                return;
            }else if(database == null){
                println("데이터베이스를 먼저 열어주세요");
                return;
            }else{
                Cursor cursor = database.rawQuery(sql, null);
                int cnt = cursor.getCount();
                println("레코드 갯수: " + cnt);
                for(int i=0; i<cnt; i++){
                    cursor.moveToNext();
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    int age = cursor.getInt(2);
                    String mobile = cursor.getString(3);
                    println("레코드 # " + i + " : " + id + name + age + mobile);
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void println(String data) {
        textView.append(data + "\n");
    }

}