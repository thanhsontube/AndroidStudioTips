package nt.son.androidstudiotips.otto;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OttoFragmentBottom extends BaseFragment {

    private TextView txtBtm;

    public OttoFragmentBottom() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otto_bottom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SingletonBus.register(this);
        initLayout(view);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initLayout(View view) {
        txtBtm = (TextView) view.findViewById(R.id.otto_txt_bottom);
    }

    @Override
    public void initListener() {

    }
    @Subscribe public void getMsgFromTop(OttoDto dto) {
        txtBtm.setText(dto.name);
    }
}
