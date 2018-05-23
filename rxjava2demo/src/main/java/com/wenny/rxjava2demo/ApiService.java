package com.wenny.rxjava2demo;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/5/23.
 */

public interface ApiService {
    @GET("api/4/news/latest")
    Call<ResponseBody> getdata();
}
