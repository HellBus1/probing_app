package com.example.phrobingapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PelangganSerializable implements Serializable {
    private Integer pelangganId;
    private Integer rekapId;
    private String idPlnPelanggan;
    private String namaUsaha;
    private String alamat;
    private String jenisUsaha;
    private String telefon;
    private String fax;
    private String contactPerson;
    private String email;
    private String tipeIndustri;
    private String tarif;
    private String dayaBeli;
    private Integer bulan;
    private Integer tahun;
    private String unitulp;

    public PelangganSerializable(Integer pelangganId, Integer rekapId, String idPlnPelanggan, String namaUsaha, String alamat,
                                 String jenisUsaha, String telefon, String fax, String contactPerson, String email, String tipeIndustri,
                                 String tarif, String dayaBeli, Integer bulan, Integer tahun, String unitulp) {
        this.pelangganId = pelangganId;
        this.rekapId = rekapId;
        this.idPlnPelanggan = idPlnPelanggan;
        this.namaUsaha = namaUsaha;
        this.alamat = alamat;
        this.jenisUsaha = jenisUsaha;
        this.telefon = telefon;
        this.fax = fax;
        this.contactPerson = contactPerson;
        this.email = email;
        this.tipeIndustri = tipeIndustri;
        this.tarif = tarif;
        this.dayaBeli = dayaBeli;
        this.bulan = bulan;
        this.tahun = tahun;
        this.unitulp = unitulp;
    }

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

    public String getUnitulp() {
        return unitulp;
    }

    public void setUnitulp(String unitulp) {
        this.unitulp = unitulp;
    }
}
