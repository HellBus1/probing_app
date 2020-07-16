package com.example.phrobingapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pelanggan {
    @SerializedName("pelanggan_id")
    @Expose
    private Integer pelangganId;
    @SerializedName("rekap_id")
    @Expose
    private Integer rekapId;
    @SerializedName("id_pln_pelanggan")
    @Expose
    private String idPlnPelanggan;
    @SerializedName("nama_usaha")
    @Expose
    private String namaUsaha;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("jenis_usaha")
    @Expose
    private String jenisUsaha;
    @SerializedName("telefon")
    @Expose
    private String telefon;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("contact_person")
    @Expose
    private String contactPerson;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("tipe_industri")
    @Expose
    private String tipeIndustri;
    @SerializedName("tarif")
    @Expose
    private String tarif;
    @SerializedName("daya_beli")
    @Expose
    private String dayaBeli;
    @SerializedName("bulan")
    @Expose
    private Integer bulan;
    @SerializedName("tahun")
    @Expose
    private Integer tahun;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("growth_rate")
    @Expose
    private String growthRate;
    @SerializedName("jam_pakai")
    @Expose
    private Integer jamPakai;
    @SerializedName("id_pln_unit")
    @Expose
    private String idPlnUnit;
    @SerializedName("ulp_unit")
    @Expose
    private String ulpUnit;

    public Integer getPelangganId() {
        return pelangganId;
    }

    public void setPelangganId(Integer pelangganId) {
        this.pelangganId = pelangganId;
    }

    public Integer getRekapId() {
        return rekapId;
    }

    public void setRekapId(Integer rekapId) {
        this.rekapId = rekapId;
    }

    public String getIdPlnPelanggan() {
        return idPlnPelanggan;
    }

    public void setIdPlnPelanggan(String idPlnPelanggan) {
        this.idPlnPelanggan = idPlnPelanggan;
    }

    public String getNamaUsaha() {
        return namaUsaha;
    }

    public void setNamaUsaha(String namaUsaha) {
        this.namaUsaha = namaUsaha;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisUsaha() {
        return jenisUsaha;
    }

    public void setJenisUsaha(String jenisUsaha) {
        this.jenisUsaha = jenisUsaha;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipeIndustri() {
        return tipeIndustri;
    }

    public void setTipeIndustri(String tipeIndustri) {
        this.tipeIndustri = tipeIndustri;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getDayaBeli() {
        return dayaBeli;
    }

    public void setDayaBeli(String dayaBeli) {
        this.dayaBeli = dayaBeli;
    }

    public Integer getBulan() {
        return bulan;
    }

    public void setBulan(Integer bulan) {
        this.bulan = bulan;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(String growthRate) {
        this.growthRate = growthRate;
    }

    public Integer getJamPakai() {
        return jamPakai;
    }

    public void setJamPakai(Integer jamPakai) {
        this.jamPakai = jamPakai;
    }

    public String getIdPlnUnit() {
        return idPlnUnit;
    }

    public void setIdPlnUnit(String idPlnUnit) {
        this.idPlnUnit = idPlnUnit;
    }

    public String getUlpUnit() {
        return ulpUnit;
    }

    public void setUlpUnit(String ulpUnit) {
        this.ulpUnit = ulpUnit;
    }
}
