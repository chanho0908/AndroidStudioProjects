package org.techtown.databasemission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnBookCallBack{

    TabLayout tabs;

    Fragment fragment;
    Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new Fragment1();
        fragment2 = new Fragment2();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment);

        tabs = findViewById(R.id.tab);
        tabs.addTab(tabs.newTab().setText("입력"));
        tabs.addTab(tabs.newTab().setText("조회"));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;

                if(position == 0){
                    selected = fragment;
                }else if(position == 1){
                    selected = fragment2;
                }
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container , selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });


    }

    @Override
    public void insert(String name, String author, String contents) {

    }

    @Override
    public ArrayList<BookInfo> selectAll() {
        return null;
    }
}