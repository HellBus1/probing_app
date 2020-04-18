package com.example.phrobingapp.connection;

import com.example.phrobingapp.login_serv.Login_pojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<Login_pojo> login_conn(@Field("email") String no_uasbn,
                                @Field("password") String tanggal_lahir);

//    @FormUrlEncoded
//    @POST("submitdatamobile")
//    Call<PostDataPojo> post_data(@Field("token") String token,
//                                 @Field("no_uasbn") String no_uasbn,
//                                 @Field("foto") String foto,
//                                 @Field("ttd") String ttd,
//                                 @Field("latitude") String latitude,
//                                 @Field("longitude") String longitude);
}
