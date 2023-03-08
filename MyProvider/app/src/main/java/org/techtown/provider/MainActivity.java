package org.techtown.provider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPerson();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryPerson();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePerson();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePerson();
            }
        });
    }


    public void insertPerson(){
        println("insertPerson 호출됨");

        String uriStr = "content://org.techtown.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        // 해당 Uri에 있는 값 반환 후 columns 배열에 저장
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String[] columns = cursor.getColumnNames();
        int cnt = columns.length;
        println("columns count ->" + cnt);

        for(int i=0; i < cnt; i++){
            println("#" + i + ":" + columns[i]);
        }

        ContentValues values = new ContentValues();
        values.put("name", "ChanHo");
        values.put("age", 20);
        values.put("mobile", "010-5206-2431");
        uri = getContentResolver().insert(uri, values);

        println("Insert 결과: " + uri.toString());

    }

    private void queryPerson() {
        try {
            Uri uri = new Uri.Builder().build().parse("content://org.techtown.provider/person");

            //배열에 지정된 column만 조회 가능
            String[] columns = {"name", "age", "mobile"};
            Cursor cursor = getContentResolver().query(uri, columns, null, null, "name ASC");
            println("결과: " + cursor.getCount());

            int idx = 0;
            while (cursor.moveToNext()){
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(columns[0]));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(columns[1]));
                @SuppressLint("Range") String mobile = cursor.getString(cursor.getColumnIndex(columns[2]));
                println("#" + idx + "->" + name + "/" + age + "/" + "mobile");
                idx++;
            }
        }catch (Exception e){e.printStackTrace();}

    }

    private void updatePerson() {
        Uri uri = new Uri.Builder().build().parse("content://org.techtown.provider/person");
        String selection = "mobile=?";
        String[] selectionArgs = {"010-5206-2431"};
        ContentValues values = new ContentValues();
        values.put("mobile", "010-3158-2431");

        int cnt = getContentResolver().update(uri, values, selection, selectionArgs);
        println("update 결과" + cnt);
    }

    private void deletePerson() {
        Uri uri = new Uri.Builder().build().parse("content://org.techtown.provider/person");
        String selection = "name=?";
        String[] selectionArgs = {"jhon"};
        int cnt = getContentResolver().delete(uri, selection, selectionArgs);
        println("update 결과" + cnt);

    }

    private void println(String s){
        textView.append(s+"\n");
    }
}