package org.techtown.calender;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
*  [gradle.properties]
*  android.useAndroidX=true
*  android.enableJetifier=true
*
*  [build.gradle(Module)]
*  implementation 'com.github.pedroSG94:AutoPermissions:1.0.3'
*
*  [setting.gradle]
*  dependencyResolutionManagement {
*  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
*  repositories {
*      google()
*      mavenCentral()
*      maven { url "https://jitpack.io" }
*      jcenter();
*    }
*  }
*
* 1. Calender 객체 생성 후 리스너 등록
* 2. Date 객체 생성후 Calender 객체에서 시간을 받음
* 3. SimpleDateFormat 으로 날짜 입력 형태 변환 후 버튼에 setText
* 4. 버튼 맨 초기 화면에 현재 날짜 표시
* 5. 달력을 보여주기 위한 showCalender
* 6. 버튼에서 날짜를 읽어와 받아올 String 객체를 받아와 format에서 parse
* 7. parse한 데이터를 Date 객체에 전달
* 8. 전달받은 Date 객체를 통해 Calender 객체에 setTime();
* 9. 날짜 전달받은 Calender 객체 정보를 통해 DatePickerDialog 객체 생성 (context, listener, year, month, day)
* 10. show
* */
public class MainActivity extends AppCompatActivity {
    Date selectedDate;
    public static SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");

    Button calenderButton;
    EditText birthday;

    Calendar myCalender = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            myCalender.set(Calendar.YEAR, year);
            myCalender.set(Calendar.MONTH, month);
            myCalender.set(Calendar.DAY_OF_MONTH, day);

            Date curDate = myCalender.getTime();
            setTextTime(curDate);
        }
    };

    private void setTextTime(Date curDate) {
        selectedDate = curDate;
        String time = format.format(selectedDate);
        calenderButton.setText(time);
        EditText editText = findViewById(R.id.birthday);
        birthday.setText(time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calenderButton = findViewById(R.id.calenderButton);
        birthday = findViewById(R.id.birthday);
        calenderButton.setText(format.format(System.currentTimeMillis()));

        calenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { showCalender(); }
        });
    }

    private void showCalender() {
        String birthDate = calenderButton.getText().toString();
        Calendar calendar = Calendar.getInstance();
        Date selectedDate = new Date();

        try {
            selectedDate = format.parse(birthDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        calendar.setTime(selectedDate);
        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, listener, curYear, curMonth, curDay);
        dialog.show();
    }
}