package org.techtown.sampleevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView = findViewById(R.id.textView);
    View view = findViewById(R.id.view);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    println("손가락 눌림" + curX + "," + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    println("손가락 떼짐" + curX + "," + curY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    println("손가락 d움직임" + curX + "," + curY);
                }
                return true;
            }
        });
    }
        public void println(String s){
            textView.append(s +"\n");
    }

}