package nt.son.androidstudiotips.otto;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseFragment;
import nt.son.androidstudiotips.main.MainDto;

/**
 * A simple {@link Fragment} subclass.
 */
public class OttoFragmentTop extends BaseFragment {


    private TextView txtTop;
    public OttoFragmentTop() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otto_top, container, false);
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
        txtTop = (TextView) view.findViewById(R.id.otto_txt_top);
        view.findViewById(R.id.otto_btn_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingletonBus.post(new OttoDto("Top Fragment"));
            }
        });


    }


    @Override
    public void initListener() {

    }
    @Subscribe public void getDataFromMainOttoActivity (MainDto dto){
        if (dto != null && txtTop!= null) {
            txtTop.setText(dto.name);
        }
    }

//    @Produce
//    public MainDto getDataFromMainOttoActivity () {
//        return getDataFromMainOttoActivity("getData1St");
//    }

}
