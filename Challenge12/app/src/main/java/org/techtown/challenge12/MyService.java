package org.techtown.challenge12;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null){
            String command = intent.getStringExtra("command");
            if(command != null){
                sendToActivity(command);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendToActivity(String command) {
        Intent ActivityIntent = new Intent();
        ActivityIntent.setAction("org.techtown.broadcast.SHOW");
        ActivityIntent.putExtra("command", command);
        sendBroadcast(ActivityIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}