package com.example.serviceimplementing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ActionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("ACTION_BUTTON_CLICK".equals(intent.getAction())) {
            //////////Handle the action button click here/////////////
            context.stopService(new Intent(context,MusicService.class));
            Toast.makeText(context, "Music Service Stopped!", Toast.LENGTH_SHORT).show();
        }
    }
}
