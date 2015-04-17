package nt.son.androidstudiotips.otto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nt.son.androidstudiotips.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OttoFragmentTop extends Fragment {


    public OttoFragmentTop() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otto_top, container, false);
    }


}
