package com.example.phrobingapp;

import com.example.phrobingapp.login_serv.Penyulang;
import com.example.phrobingapp.login_serv.Unit;

import java.util.List;

public class Konstanta {
    public static String [] tarifs = {
      "Daftar tarif",
      "S1",
      "S2",
      "S3",
      "R1",
      "R1M",
      "R2",
      "R3",
      "B1",
      "B2",
      "B3",
      "I1",
      "I2",
      "I3",
      "I4",
      "P1",
      "P2",
      "P3",
      "T",
      "C/TM",
      "L",
    };

    public static String [] tipeIndustri = {
        "Perikanan",
            "Peternakan",
            "Kehutanan",
            "Tanaman Pangan dan Perkebunan",
            "Pertambangan",
            "Industri instrumen",
            "Industri kulit",
            "Industri kayu",
            "Industri tekstil",
            "Industri karet",
            "Industri mineral non",
            "Industri kertas",
            "Industri alat angkutan",
            "Industri kimia dasar",
            "Industri makanan dan minuman",
            "Industri logam",
            "Industri farmasi",
            "Perdagangan dan Reparasi",
            "Hotel dan Restoran",
            "Konstruksi",
            "Perumahan, Kawasan",
            "Listrik, Gas, dan Air",
            "Transportasi, Gudang"
    };

    public static String [] multigunas = {
            "Daftar tarif",
            "LS1",
            "LS2",
            "LS3",
            "LR1",
            "LR1M",
            "LR2",
            "LR3",
            "LB1",
            "LB2",
            "LB3",
            "LI1",
            "LI2",
            "LI3",
            "LI4",
            "LP1",
            "LP2",
            "LP3",
            "LT",
            "LC/TM",
    };

    public static String [] dayas = {
       "Daftar daya",
       "220",
       "450",
       "900",
       "1.300",
       "2.200",
       "3.500 s/d 200.000",
       "200.000",
       "3.500 s/d 5.500",
       "6.600 keatas",
       "2.200 s/d 5.500",
       "6.600 s/d 200.000",
       "200.000 keatas",
       "3.500 s/d 14.000",
       "14.000 s/d 200.000",
       "30.000.000 keatas",
    };

    public static String [] keterangan = {"Pasang Baru",
            "Perubahan Daya", "Premium", "Multiguna", "Lain - Lain"};

    public static String [] premium = {
            "Gold", "Silver", "Bronze", "Platinum"
    };

    public static List<Unit> units;
    public static List<Penyulang> penyulangs;
}
