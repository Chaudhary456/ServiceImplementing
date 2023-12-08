package com.example.serviceimplementing;

import static com.example.serviceimplementing.CreateNotificationChannel.channelId;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


public class MusicService extends Service {
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        ////////////////Accessing the default ringtone of Android Device//////////////////
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        ///////////////Create an action for the notification/////////////////
        Intent actionIntent = new Intent(this, ActionReceiver.class);
        actionIntent.setAction("ACTION_BUTTON_CLICK");
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(this, 0, actionIntent, 0);

        startForeground(1, createNotification(actionPendingIntent));

        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        mediaPlayer.stop();
        super.onDestroy();
    }

    private Notification createNotification(PendingIntent actionPendingIntent) {
        //////////Customize your notification/////////
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("Music Service")
                .setContentText("Playing")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .addAction(R.drawable.ic_action_button, "Stop Music", actionPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_LOW);


        return builder.build();
    }
}
