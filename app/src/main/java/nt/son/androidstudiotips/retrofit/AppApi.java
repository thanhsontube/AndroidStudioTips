package nt.son.androidstudiotips.retrofit;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;

import nt.son.androidstudiotips.otto.SingletonBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by Sonnt on 4/21/15.
 */
public class AppApi {
    public static final String TAG = "AppApi";
    public static AppApi instance = null;
    private RestAdapter restAdapter;
    public static final String BASE_URL = "http://demo5769502.mockable.io/";
    private IAppApi mService;

    public static void createInstance(Context context) {
        instance = new AppApi(context);
    }

    public static AppApi getInstance() {
        return instance;
    }

    public AppApi (Context context) {
        init();
    }
    private void init() {
        restAdapter = getRestAdapter();
        mService = restAdapter.create(IAppApi.class);

    }
    private static RestAdapter getRestAdapter() {
        return new RestAdapter.Builder().setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(new GsonBuilder().serializeNulls().create()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public void getRepoGithub (String user){
        mService.getdata(new Callback<Data1Dto>() {


            @Override
            public void success(Data1Dto s, Response response) {
                Log.d(TAG, ">>>" + "success:" + s.msg);
                SingletonBus.post(s.msg);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, ">>>" + "failure:" + error.toString());
                SingletonBus.post("failure:" + error.toString());

            }
        });
    }


}
