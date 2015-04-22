package nt.son.androidstudiotips.retrofit;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Sonnt on 4/21/15.
 */
public interface IAppApi {

    @GET("/getdata")
    void getdata (Callback<Data1Dto> callback);
}
