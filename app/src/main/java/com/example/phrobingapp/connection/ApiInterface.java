package com.example.phrobingapp.connection;

import com.example.phrobingapp.login_serv.Login_pojo;
import com.example.phrobingapp.pojo.GetListTO;
import com.example.phrobingapp.pojo.SubmitData;
import com.example.phrobingapp.pojo.iklan.GetListIklan;

import java.io.File;
import java.util.Date;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<Login_pojo> login_conn(@Field("email") String no_uasbn,
                                @Field("password") String tanggal_lahir);
//    RequestBody
    @Multipart
    @POST("probing_new")
    Call<SubmitData> post_data(@Part("unit") RequestBody unit,
                               @Part MultipartBody.Part foto,
                               @Part("penyulang") RequestBody penyulang,
                               @Part("nama_usaha") RequestBody nama_usaha,
                               @Part("alamat") RequestBody alamat,
                               @Part("nomor_telp") RequestBody nomor_telp,
                               @Part("cp") RequestBody cp,
                               @Part("fax") RequestBody fax,
                               @Part("email") RequestBody email,
                               @Part("jenis_usaha") RequestBody jenis_usaha,
                               @Part("tipe_industri") RequestBody tipe_industri,
                               @Part("keterangan") RequestBody keterangan,
                               @Part("tarif") RequestBody tarif,
                               @Part("daya") RequestBody daya,
                               @Part("tanggal") RequestBody tanggal,
                               @Part("latlong") RequestBody latlong,
                               @Part("keterangan_tambahan") RequestBody keterangan_tambahan);

    @Multipart
    @POST("probing_existing")
    Call<SubmitData> update_data(@Part MultipartBody.Part foto,
                                 @Part("id_pelanggan") RequestBody id_pelanggan,
                                 @Part("tarif") RequestBody tarif,
                                 @Part("daya") RequestBody daya,
                                 @Part("nama_usaha") RequestBody nama_usaha,
                                 @Part("alamat") RequestBody alamat,
                                 @Part("tanggal") RequestBody tanggal,
                                 @Part("keterangan") RequestBody keterangan,
                                 @Part("lokasi") RequestBody lokasi,
                                 @Part("keterangan_lainnya") RequestBody keterangan_lainnya,
                                 @Part("rekap_id") RequestBody rekap_id);

    @Multipart
    @POST("uploader")
    Call<SubmitData> post_foto(@Part MultipartBody.Part foto,
                               @Part("nama_usaha") RequestBody nama_usaha,
                               @Part("alamat") RequestBody alamat,
                               @Part("nomor_telp") RequestBody nomor_telp,
                               @Part("cp") RequestBody cp,
                               @Part("fax") RequestBody fax,
                               @Part("email") RequestBody email,
                               @Part("latlong") RequestBody latlong,
                               @Part("id") RequestBody id,
                               @Part("flag") RequestBody flag);

    @FormUrlEncoded
    @POST("target-ops")
    Call<GetListTO> getTo(@Field("user_id") Integer id);

    @GET("iklan")
    Call<GetListIklan> getIklan();
}
