package txt.com.webview_loading;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessaging extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMessaging";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            showNotification(remoteMessage.getNotification().getBody());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void showNotification(String message){
        Intent intent = new Intent(this, TestBackActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        @SuppressLint("ResourceAsColor") Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(message)
                .setSmallIcon(R.drawable.logo_ovn_splashscreen)
                .setColor(R.color.colorAccent)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PUBLIC) //Thông báo ngoài màn hình khóa
                .addAction(R.drawable.notify, "Previous",pIntent) // #0
                .addAction(R.drawable.notify, "Pause", pIntent)  // #1
                .addAction(R.drawable.notify, "Next", pIntent)     // #2
                .setFullScreenIntent(pIntent, true); // Nội dung hiển thị trên màn hình tức thì khi nhận thông báo.

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            builder.setChannelId("43002");
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("43002", "Chuẩn", NotificationManager.IMPORTANCE_HIGH);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        Notification n = builder.build();

        if(notificationManager != null){
            notificationManager.notify(0,n);
        }



    }


}
