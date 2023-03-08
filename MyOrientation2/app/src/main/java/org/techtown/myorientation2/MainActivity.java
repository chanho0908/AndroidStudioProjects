package org.techtown.myorientation2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showToast("가로 방향");
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            showToast("세로 방향");
        }
    }
    public void showToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}