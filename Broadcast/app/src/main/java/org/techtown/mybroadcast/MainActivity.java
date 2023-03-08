package org.techtown.mybroadcast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    public static final int PERMISSION_CODE = 101;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, PERMISSION_CODE, permissions, this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoPermissions.Companion.loadAllPermissions(this, PERMISSION_CODE);

    }

    @Override
    public void onDenied(int requsetCode, String[] permissions) {
        showToast("권한이 거부된 것:" + permissions.length + "개");
    }

    @Override
    public void onGranted(int requsetCode, String[] permissions) {
        showToast("권한이 허가된 것:" + permissions.length + "개");
    }

    public void showToast(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}