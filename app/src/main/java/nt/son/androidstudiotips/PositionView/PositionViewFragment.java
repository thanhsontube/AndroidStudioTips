package nt.son.androidstudiotips.PositionView;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseFragment;
import nt.son.androidstudiotips.utils.Logger;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PositionViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PositionViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PositionViewFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "PositionViewFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextView txt;
    private Button btn1, btn2, btn3;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PositionViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PositionViewFragment newInstance(String param1, String param2) {
        PositionViewFragment fragment = new PositionViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PositionViewFragment() {
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
        return inflater.inflate(R.layout.fragment_position_view, container, false);
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initLayout(View view) {
        txt = (TextView) view.findViewById(R.id.txt_position);
        btn1 = (Button) view.findViewWithTag("btn1");
        btn2 = (Button) view.findViewWithTag("btn2");
        btn3 = (Button) view.findViewWithTag("btn3");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePosition(btn1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePosition(btn2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePosition(btn3);
            }
        });

    }

    @Override
    public void initListener() {

    }

    private void calculatePosition (final View view) {
        txt.setText("calculatePosition:" + view.toString());

        int[] arr = new int[4];
        view.getLocationOnScreen(arr);
        for (int i = 0; i < arr.length; i++) {
            Logger.debug(TAG, ">>>" + i +":" + arr[i] );
        }

//        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        int w = view.getWidth();
//        int h = view.getHeight();
//        txt.setText("w:" + w + ";h:" + h);

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rootView = layoutInflater.inflate(R.layout.my_popup_window, null, false);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int w = view.getWidth();
                int h = view.getHeight();
                txt.setText("w:" + w + ";h:" + h);
//
//                final PopupWindow popupWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 200, 200);
//
//                popupWindow.showAsDropDown(view, 200, 200);

                DemoPopupWindow betterPopupWindow = new DemoPopupWindow(view);

                betterPopupWindow.showLikeQuickAction(200,200);



            }
        });
    }

    private static class DemoPopupWindow extends BetterPopupWindow  {
        public DemoPopupWindow(View anchor) {
            super(anchor);
        }

        @Override
        protected void onCreate() {
            // inflate layout
            LayoutInflater inflater =
                    (LayoutInflater) this.anchor.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            ViewGroup root = (ViewGroup) inflater.inflate(R.layout.my_popup_window, null);



            // set the inflated view as what we want to display
            this.setContentView(root);
        }


    }
}
