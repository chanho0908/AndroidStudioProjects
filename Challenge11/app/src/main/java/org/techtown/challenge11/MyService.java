package org.techtown.challenge11;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "Myservice";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "OnCreate 호출");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand 호출");
        if(intent != null){
            processCommand(intent);
        }else{
            return Service.START_STICKY;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        if(intent != null){
            String command = intent.getStringExtra("command");
            if(command != null){
                Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
                showIntent.addFlags(showIntent.FLAG_ACTIVITY_NEW_TASK|
                        showIntent.FLAG_ACTIVITY_SINGLE_TOP|showIntent.FLAG_ACTIVITY_CLEAR_TOP);
                showIntent.putExtra("command", command);
                startActivity(showIntent);
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestory 호출");

    }
}