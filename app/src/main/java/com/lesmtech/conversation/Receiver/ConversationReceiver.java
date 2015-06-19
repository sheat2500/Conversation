package com.lesmtech.conversation.Receiver;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lesmtech.conversation.MainActivity;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Rindt
 * @version 0.1
 * @since 6/19/15
 */
public class ConversationReceiver extends ParsePushBroadcastReceiver {

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);
        Log.d("Receive", "Success");
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String message = extras.getString("com.parse.Data");
        try {
            // {"alert":"asddasd","push_hash":"d8cb3730f220c05d81c4a8efabb497e8"}
            // Retrive alert and send message to HomeActivity
            JSONObject object = new JSONObject(message);
            String result = object.getString("alert");
            Log.d("Receive", result);
            Intent launchHomeActivity = new Intent(context, MainActivity.class);
            launchHomeActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchHomeActivity.putExtra(MainActivity.TYPE, MainActivity.TYPE_UPDATE);
            launchHomeActivity.putExtra("message", result);
            context.startActivity(launchHomeActivity);
        } catch (Exception e) {

        }
//        Log.d("Receive", message);
    }

    @Override
    protected Notification getNotification(Context context, Intent intent) {
        return super.getNotification(context, intent);
    }
}
