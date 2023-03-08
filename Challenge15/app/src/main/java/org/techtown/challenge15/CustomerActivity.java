package org.techtown.challenge15;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CustomerActivity extends AppCompatActivity {
    Button calenderButton;
    EditText editText;

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    Date seletectedDate;

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            Date curDate = calendar.getTime();
            upDateText(curDate);
        }
    };

    private void upDateText(Date curDate){
        seletectedDate = curDate;
        String str = format.format(seletectedDate);
        calenderButton.setText(str);
        editText.setText(str);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        editText = findViewById(R.id.birthday);
        calenderButton = findViewById(R.id.calenderButton);
        calenderButton.setText(format.format(System.currentTimeMillis()));

        calenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { showCalender(); }
        });

    }

    private void showCalender(){
        String birthDateStr = calenderButton.getText().toString();

        Calendar calendar = Calendar.getInstance();
        Date curBirthDate = new Date();
        try {
            curBirthDate = format.parse(birthDateStr);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        calendar.setTime(curBirthDate);

        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, listener, curYear, curMonth, curDay);
        dialog.show();
    }
}