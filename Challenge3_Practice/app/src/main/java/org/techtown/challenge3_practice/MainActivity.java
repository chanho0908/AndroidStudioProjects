package org.techtown.challenge3_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    ImageView imageView2;
    Button Button1;
    Button Button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView4);
        imageView2 = findViewById(R.id.imageView5);
        Button1 = findViewById(R.id.button1);
        Button2 = findViewById(R.id.button2);

        Button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonUp();
            }
        });

        Button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onButtonDown();
            }
        });

        onButtonUp();
    }

    public void onButtonUp(){
        imageView1.setImageResource(R.drawable.ddung);
        imageView2.setImageResource(0);
        Toast.makeText(getApplicationContext(), "뚱이!",Toast.LENGTH_SHORT).show();
    }

    public void onButtonDown(){
        imageView1.setImageResource(0);
        imageView2.setImageResource(R.drawable.sponge);
        Toast.makeText(getApplicationContext(), "스폰지밥!",Toast.LENGTH_SHORT).show();
    }

}