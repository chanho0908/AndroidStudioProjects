package org.techtown.challenge3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    ImageView imageView2;
    Button button1;
    Button button2;
    int btnIdx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                moveImageUp();
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                moveImageDown();
            }
        });
        moveImageUp();
    }


    public void moveImageDown() {
        imageView1.setImageResource(0);
        imageView2.setImageResource(R.drawable.sponge);
        Toast.makeText(getApplicationContext(), "스폰지밥!", Toast.LENGTH_LONG).show();
        //imageView1.invalidate();
        //imageView2.invalidate();
    }

    public void moveImageUp() {
        imageView1.setImageResource(R.drawable.ddung);
        imageView2.setImageResource(0);
        Toast.makeText(getApplicationContext(), "뚱이!", Toast.LENGTH_SHORT).show();
        //imageView1.invalidate();
        //imageView2.invalidate();
    }


}