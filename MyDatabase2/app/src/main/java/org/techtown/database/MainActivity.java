package org.techtown.database;

import androidx.appcompat.app.AppCompatActivity;

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

    SQLiteDatabase database;

    String TableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        TableName = editText2.getText().toString();

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTable(TableName);
                insertRecord();
            }
        });
    }

    private void createDatabase(String name) {
        println("createDatabase 호출");
        database = openOrCreateDatabase(name, MODE_PRIVATE, null);
        println("데이터베이스 생성 ");
    }

    private void createTable(String tableName) {
        println("createTable 호출");
        database.execSQL( " CREATE TABLE " + tableName + "(" +
                "NAME TEXT" +
                "AGE INTEGER" +
                "MOBILE TEXT" +
                ")"
        );

        println("테이블 생성 ");

    }

    private void insertRecord() {
        println("insertRecord 호출");

        if(database != null && TableName != null){
            String INSERT_SQL =  " INSERT INTO " + TableName + " (NAME, AGE, MOBILE) " + "VALUES" +
                    "('JHON', 20, '010-3158-2431')";
            database.execSQL(INSERT_SQL);

            println("레코드 추가 ");

        }

    }


    public void println(String data){
        textView.append(data + "\n");
    }

}