package nt.son.androidstudiotips.shape;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShapeFragment extends BaseFragment {


    public ShapeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shape, container, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initLayout(View view) {

    }

    @Override
    public void initListener() {

    }
}
