package com.example.cookbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cookbook.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static final String TAG = "MainActivity";
    private static String CHANNEL_ID = "CookBook Notification Channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel =
//                    new NotificationChannel(CHANNEL_ID, "CookBook Notification",
//                            NotificationManager.IMPORTANCE_DEFAULT);
//
//            NotificationManager mNotificationManager =
//                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            mNotificationManager.createNotificationChannel(notificationChannel);
//
//            NotificationCompat.Builder mBuilder =
//                    new NotificationCompat.Builder(this, CHANNEL_ID)
//                            .setSmallIcon(R.drawable.ic_launcher_background)
//                            .setContentTitle("It seems you are hungry")
//                            .setContentText("What are you wanna make today ?");
//
//            mNotificationManager.notify(1,mBuilder.build());
//        }
    }

}