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

    // 방향 전환시 호출
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // 가로 방향인가?
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showToast("가로 방향");
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //세로 방향
            showToast("세로 방향");
        }
    }

    public void showToast(String data){
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}