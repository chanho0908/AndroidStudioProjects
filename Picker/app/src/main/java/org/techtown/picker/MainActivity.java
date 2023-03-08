package org.techtown.picker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/*
   1. DatePickerDialog 리스너 생성
      캘린더 객체를 생성해 날짜 set
      Date 객체 생성 후 생성된 캘린더의 시간을 get
      SimpleDateFormet으로 형태 변환
      버튼에 set
   2. 오늘 날짜를 받아와 버튼에 기본으로 오늘 날짜 표시
   3. 버튼 클릭시 버튼의 날짜 get
      Date 객체 생성 후 get한 날짜 set
      선택된 날짜가 달력에 표시
   4. DatePickerDialog 객체 생성 후 show
   * */

public class MainActivity extends AppCompatActivity {
    Button button;
    Date date;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");

    Calendar selectedCalender = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener callbackMethod = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            selectedCalender.set(Calendar.YEAR, year);
            selectedCalender.set(Calendar.MONTH, month);
            selectedCalender.set(Calendar.DAY_OF_MONTH, day);

            date = selectedCalender.getTime();
            String str = dateFormat.format(date);
            button.setText(str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        // 현재 시간 가져옴
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        button.setText(dateFormat.format(date));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { showDialog(); }
        });
    }

    public void showDialog(){
        Calendar Mycalender = Calendar.getInstance();
        String birthstr = button.getText().toString();
        Date curBirthDate = new Date();

        try {
            curBirthDate = dateFormat.parse(birthstr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Mycalender.setTime(curBirthDate);
        int myear = Mycalender.get(Calendar.YEAR);
        int mmonth = Mycalender.get(Calendar.MONTH);
        int mday = Mycalender.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod,
                myear, mmonth, mday);
        dialog.show();
    }


}