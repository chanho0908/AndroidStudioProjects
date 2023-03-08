package org.techtown.challenge12;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    MyReceiver receiver;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String command =  editText.getText().toString();
                sendToService(command);
            }
        });

        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("org.techtown.broadcast.SHOW");
        registerReceiver(receiver, filter);
    }

    private void sendToService(String command) {
        Intent Serviceintent = new Intent(getApplicationContext(), MyService.class);
        Serviceintent.putExtra("command", command);
        startService(Serviceintent);
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            processIntent(intent);
        }
    }

    private void processIntent(Intent intent) {
        if(intent != null){
            String text = intent.getStringExtra("command");
            if(text != null){
                String str = textView.getText().toString();
                textView.setText(str + text);
            }
        }
    }

}