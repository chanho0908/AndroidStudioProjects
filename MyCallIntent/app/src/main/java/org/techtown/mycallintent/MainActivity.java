package org.techtown.mycallintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //사용자의 요청 정보를 담고 있는 객체
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-000-000"));
                startActivity(intent);

                /*
                Intent intent = new Intent();
                // Component : Applicaiton 구성 요소
                // 특정 Activity를 가르키는데 사용 가능
                ComponentName name = new ComponentName("org.techtown.mycallintent",
                        "org.techtown.mycallintent.MenuActivity");
                intent.setComponent(name);
                startActivityForResult(intent, 101);
                 */
            }
        });
    }


}