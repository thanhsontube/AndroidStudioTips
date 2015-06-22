package nt.son.androidstudiotips.fresco;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FrescoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FrescoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrescoFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SimpleDraweeView simpleDraweeView;

    private OnFragmentInteractionListener mListener;
    String URL1 = "http://images6.fanpop.com/image/photos/34300000/T-ara-t-ara-tiara-34382642-1920-1080.jpg";
    String URLERROR = "123http://images6.fanpop.com/image/photos/34300000/T-ara-t-ara-tiara-34382642-1920-1080.jpg";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrescoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FrescoFragment newInstance(String param1, String param2) {
        FrescoFragment fragment = new FrescoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FrescoFragment() {
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
        return inflater.inflate(R.layout.fragment_fresco, container, false);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initImage();
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
        simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.my_image_view);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onResume() {
        super.onResume();
        initImage();
    }

    private void initImage () {
        Uri uri = Uri.parse(URL1);
//        Uri uri = Uri.parse(URLERROR);
        DraweeController dc = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .setOldController(new DraweeController() {
                    @Override
                    public DraweeHierarchy getHierarchy() {
                        return null;
                    }

                    @Override
                    public void setHierarchy(@Nullable DraweeHierarchy draweeHierarchy) {

                    }

                    @Override
                    public void onAttach() {

                    }

                    @Override
                    public void onDetach() {

                    }

                    @Override
                    public boolean onTouchEvent(MotionEvent motionEvent) {
                        return false;
                    }

                    @Override
                    public Animatable getAnimatable() {
                        return null;
                    }
                })
                .setAutoPlayAnimations(true)


                .build();

        RoundingParams rp = RoundingParams.fromCornersRadii(roundPx,roundPx,roundPx,roundPx);
        simpleDraweeView.setController(dc);
        simpleDraweeView.getHierarchy().setRoundingParams(rp);


    }
    int roundPx = 80;
}
