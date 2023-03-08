package org.techtown.mytoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

    }
    public void click(View v){
        try {
            Toast toastView = Toast.makeText(getApplicationContext(), "토스트 메시지", Toast.LENGTH_LONG);
            int xoffset = Integer.parseInt(editText1.getText().toString());
            int yoffset = Integer.parseInt(editText2.getText().toString());
            toastView.setGravity(Gravity.TOP|Gravity.TOP,  xoffset, yoffset);
            toastView.show();
        }catch (NumberFormatException e){
            e.printStackTrace(); }
    }
}