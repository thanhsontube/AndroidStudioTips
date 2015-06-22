package nt.son.androidstudiotips;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import nt.son.androidstudiotips.base.BaseFragment;
import nt.son.androidstudiotips.facebook.Facebook4Activity;
import nt.son.androidstudiotips.location_map.HereMapActivity;
import nt.son.androidstudiotips.main.AdapterMain;
import nt.son.androidstudiotips.main.MainDto;
import nt.son.androidstudiotips.otto.OttoActivity;
import nt.son.androidstudiotips.sliding_tabs.SlidingTabsActivity;
import nt.son.androidstudiotips.toolbar.ToolBarActivity;
import nt.son.androidstudiotips.weak_gc_asycn.WeakActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MainFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listView;
    private AdapterMain adapter;
    private List<MainDto> list = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initLayout(view);
        initListener();

    }

    @Override
    public void initData() {
        list.clear();
        //0
        list.add(new MainDto("OTTO"));

        //1
        list.add(new MainDto("RetroFit"));

        //2
        list.add(new MainDto("WeakPreferences, GC, AsyncTask, Leak M"));

        //3 shape- gradient
        list.add(new MainDto("Shape-Gradient"));
        list.add(new MainDto("Location - Google MAP"));
        //5
        list.add(new MainDto("ToolBar"));
        //6 fb 4
        list.add(new MainDto("Facebook 4"));

        //7 position view
        list.add(new MainDto("Position View"));
        //8 sliding tabs
        list.add(new MainDto("Sliding Tabs"));

        //9 text float edittext

        list.add(new MainDto("Float EditText"));
        //10 Fresco
        list.add(new MainDto("Image Loader: Fresco"));
        //11 Notification
        list.add(new MainDto("Notification"));
    }

    @Override
    public void initLayout(View view) {
        listView = (ListView) view.findViewById(R.id.main_list_view);
        adapter = new AdapterMain(getActivity(), list);
        listView.setAdapter(adapter);


    }

    @Override
    public void initListener() {
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Crashlytics.log("onItemClick:" +position);
        Log.d(TAG, ">>>" + "click:" + position);
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(getActivity(), OttoActivity.class);
                break;
            case 1:
                mListener.onReplaceFragment(1);
                break;
            case 2:
                intent = new Intent(getActivity(), WeakActivity.class);
                break;
            case 3:
                mListener.onReplaceFragment(3);
                break;
            case 4:
                intent = new Intent(getActivity(), HereMapActivity.class);
                break;
            case 5:
                intent = new Intent(getActivity(), ToolBarActivity.class);
                break;
            case 6:
                intent = new Intent(getActivity(), Facebook4Activity.class);
                break;
            case 7 :
                mListener.onReplaceFragment(7);
                break;
            case 8:
                intent = new Intent(getActivity(), SlidingTabsActivity.class);
                break;

            case 9 :
                mListener.onReplaceFragment(9);
                break;

            case 10 :
                mListener.onReplaceFragment(10);
                break;case 11 :
                mListener.onReplaceFragment(11);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        public void onFragmentInteraction(Uri uri);

        public void onReplaceFragment(int pos);
    }

}
