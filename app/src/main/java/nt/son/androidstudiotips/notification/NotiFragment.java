package nt.son.androidstudiotips.notification;

import android.app.Fragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseFragment;
import nt.son.androidstudiotips.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotiFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotiFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotiFragment newInstance(String param1, String param2) {
        NotiFragment fragment = new NotiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NotiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_noti, container, false);
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void initData() {
        String longText = "Without BigTextStyle, only a single line of text would be visible. " +
                "Any additional text would not appear directly on the notification. " +
                "The entire first line would not even be on the notification if it were too long! " +
                "Text that doesn't fit in a standard notification becomes ellipsized. " +
                "That is, the characters that don't fit are removed and replaced by ellipsis.";
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),10, new Intent(getActivity(),MainActivity.class), 0);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle().bigText(longText);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_got_it);

        android.support.v4.app.NotificationCompat.Action action = new NotificationCompat.Action(R.drawable.icon_got_it, "action Wear", pendingIntent);
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender().addAction(action);

        NotificationCompat.BigPictureStyle bigPictureStyle = new android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(bitmap).setBigContentTitle("BigMap content title").setSummaryText("Bitmap SUmmary text");
        Notification notificationCompat = new NotificationCompat.Builder(getActivity())
                .setContentTitle("Content Title")
                .setContentText(longText)
                .setSmallIcon(R.drawable.icon_got_it)
                .setContentIntent(pendingIntent)
                .setCategory(NotificationCompat.CATEGORY_STATUS)
                .setStyle(bigTextStyle)
                .setStyle(bigPictureStyle)
                .setDefaults(NotificationCompat.COLOR_DEFAULT)
                .extend(wearableExtender)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify("tag",19, notificationCompat);
    }

    @Override
    public void initLayout(View view) {

    }

    @Override
    public void initListener() {

    }
}
