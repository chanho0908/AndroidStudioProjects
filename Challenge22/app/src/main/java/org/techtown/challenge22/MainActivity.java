package org.techtown.challenge22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnDatabaseCallBack{
    private static final String TAG = "MainActivity";

    TabLayout tab;

    Fragment fragment;
    Fragment fragment2;

    BookDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("입력"));
        tab.addTab(tab.newTab().setText("조회"));

        fragment = new Fragment1();
        fragment2 = new Fragment2();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selected = null;
                int position = tab.getPosition();

                if(position == 0){
                    selected = fragment;
                }else if(position == 1){
                    selected = fragment2;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // open database
        if (database != null) {
            database.close();
            database = null;
        }

        database = BookDatabase.getInstance(this);

        boolean isOpen = database.open();
        if (isOpen) {
            Log.d(TAG, "Book database is open.");
        } else {
            Log.d(TAG, "Book database is not open.");
        }


    }

    protected void onDestroy() {
        // close database
        if (database != null) {
            database.close();
            database = null;
        }

        super.onDestroy();
    }


    @Override
    public void insert(String name, String author, String contents) {
        database.insertRecord(name, author, contents);
        Toast.makeText(getApplicationContext(), "책 정보를 추가했습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public ArrayList<BookInfo> selectAll() {
        ArrayList<BookInfo> result = database.selectAll();
        Toast.makeText(getApplicationContext(), "책 정보를 조회했습니다.", Toast.LENGTH_LONG).show();

        return result;
    }
}