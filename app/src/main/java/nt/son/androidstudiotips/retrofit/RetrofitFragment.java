package nt.son.androidstudiotips.retrofit;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseFragment;
import nt.son.androidstudiotips.otto.SingletonBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class RetrofitFragment extends BaseFragment {


    private TextView txtResults;
    private Button btnSend;
    public RetrofitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_retrofit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SingletonBus.register(this);
        initLayout(view);
        initListener();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SingletonBus.unregister(this);
    }

    @Override
    public void initData() {


    }

    @Override
    public void initLayout(View view) {
        btnSend = (Button) view.findViewById(R.id.retrofit_btn_get);
        txtResults = (TextView) view.findViewById(R.id.retrofit_txt_result);

    }

    @Override
    public void initListener() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppApi.getInstance().getRepoGithub("thanhsontube");
            }
        });
    }
    @Subscribe
    public void getMsgRetroFit (String s) {
        if (!TextUtils.isEmpty(s)) {
            txtResults.setText(s);
        }
    }
}
