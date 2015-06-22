package nt.son.androidstudiotips.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseFragment;
import nt.son.androidstudiotips.main.MainActivity;

/**
 * Created by Sonnt on 6/22/15.
 */
public class NotificationFragment extends BaseFragment {


    @Override
    public void initData() {
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),10, new Intent(getActivity(),MainActivity.class), 0);

        Notification notificationCompat = new NotificationCompat.Builder(getActivity())
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setSmallIcon(R.drawable.icon_got_it)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
//        notificationManagerCompat.notify("tag",19, notificationCompat);
        notificationManagerCompat.notify(19, notificationCompat);
    }

    @Override
    public void initLayout(View view) {


    }

    @Override
    public void initListener() {

    }
}
