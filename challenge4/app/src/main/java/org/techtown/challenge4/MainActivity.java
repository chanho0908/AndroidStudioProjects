package org.techtown.challenge4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText editText;
        TextView textView;
        Button sendButton;
        Button closeButton;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.EditText);
        textView = findViewById(R.id.textView);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = editText.getText().toString();
                textView.setText(input.length()+"/80 글자수");
            }
            @Override
            public void afterTextChanged(Editable s) {
                //textView.setText(s.toString());
            }
        });

        sendButton = findViewById(R.id.button1);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString();
                Toast.makeText(getApplicationContext(), input, Toast.LENGTH_SHORT).show();
            }
        });

        closeButton = findViewById(R.id.button2);
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void submitButton(){

    }
}