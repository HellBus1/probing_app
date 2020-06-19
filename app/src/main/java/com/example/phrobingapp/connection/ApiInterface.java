package com.example.phrobingapp.connection;

import com.example.phrobingapp.login_serv.Login_pojo;
import com.example.phrobingapp.pojo.GetListTO;
import com.example.phrobingapp.pojo.SubmitData;

import java.io.File;
import java.util.Date;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<Login_pojo> login_conn(@Field("email") String no_uasbn,
                                @Field("password") String tanggal_lahir);
    @FormUrlEncoded
    @POST("probing_new")
    Call<SubmitData> post_data(@Field("unit") Integer unit,
                               @Field("penyulang") Integer penyulang,
                               @Field("nama_usaha") String nama_usaha,
                               @Field("alamat") String alamat,
                               @Field("nomor_telp") String nomor_telp,
                               @Field("cp") String cp,
                               @Field("fax") String fax,
                               @Field("email") String email,
                               @Field("jenis_usaha") String jenis_usaha,
                               @Field("tipe_industri") String tipe_industri,
                               @Field("keterangan") String keterangan,
                               @Field("tarif") String tarif,
                               @Field("daya") String daya,
                               @Field("tanggal") String tanggal,
                               @Field("latlong") String latlong,
                               @Field("keterangan_tambahan") String keterangan_tambahan);

    @FormUrlEncoded
    @POST("probing_existing")
    Call<SubmitData> update_data(@Field("id_pelanggan") String id_pelanggan,
                                 @Field("tarif") String tarif,
                                 @Field("daya") String daya,
                                 @Field("nama_usaha") String nama_usaha,
                                 @Field("alamat") String alamat,
                                 @Field("tanggal") String tanggal,
                                 @Field("keterangan") String keterangan,
                                 @Field("lokasi") String lokasi,
                                 @Field("keterangan_lainnya") String keterangan_lainnya,
                                 @Field("rekap_id") Integer rekap_id);

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
}
