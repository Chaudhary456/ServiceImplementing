package com.example.serviceimplementing;

import android.app.Application;
import android.app.NotificationManager;

import android.app.NotificationChannel;
import android.os.Build;

public class CreateNotificationChannel extends Application {

    public static final String channelId = "music_channel";
    public static final String channelName = "music_service";

    @Override
    public void onCreate() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_LOW);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        super.onCreate();
    }
}
