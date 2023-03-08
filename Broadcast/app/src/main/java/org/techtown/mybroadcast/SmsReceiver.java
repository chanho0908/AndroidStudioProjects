package org.techtown.mybroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SmsReceiver";
    public SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive 호출");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages =  parseSmsMessage(bundle);
        if(messages.length > 0){
            String sender = messages[0].getOriginatingAddress();
            String contents = messages[0].getMessageBody();
            Date date = new Date(messages[0].getTimestampMillis());
            String receievedDate = format.format(date);

            Log.d(TAG, "sender: " + sender + "\nContents: " + contents + "\nDate: " + receievedDate);
            sendToActivity(context, sender, contents, receievedDate);
        }
    }

    public void sendToActivity(Context context, String sender, String contents, String receievedDate){
        Intent intent = new Intent(context, SmsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender", sender);
        intent.putExtra("content", contents);
        intent.putExtra("receievedDate", receievedDate);
        context.startActivity(intent);

    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for(int i=0; i < smsCount; i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            }else {messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }

        }return messages;


    }
}