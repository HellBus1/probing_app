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
      "S3K",
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
      "C",
//      "C/TM",
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
            "LS3K",
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
            "L",
//            "LR1",
//            "LR1M",
//            "LR2",
//            "LR3",
//            "LT",
//            "LC/TM",
    };

    public static String [] dayas = {
       "Daftar daya",
        "450",
        "900",
        "1.300",
        "2.200",
        "2.400",
        "3.500",
        "3.900",
        "4.400",
        "5.500",
        "6.600",
        "7.700",
        "10.600",
        "11.000",
        "13.200",
        "13.900",
        "15.400",
        "16.500",
        "17.600",
        "22.000",
        "23.000",
        "27.800",
        "30.000",
        "35.200",
        "41.500",
        "44.000",
        "53.000",
        "66.000",
        "82.500",
        "105.000",
        "131.000",
        "145.500",
        "147.000",
        "164.000",
        "197.000",
        "200.000",
        "210.000",
        "233.000",
        "240.000",
        "245.000",
        "279.000",
        "300.000",
        "310.000",
        "329.000",
        "345.000",
        "349.400",
        "380.000",
        "400.000",
        "414.000",
        "415.000",
        "430.000",
        "433.000",
        "460.000",
        "485.000",
        "500.000",
        "520.000",
        "526.000",
        "555.000",
        "605.000",
        "630.000",
        "690.000",
        "725.000",
        "740.000",
        "760.000",
        "765.000",
        "800.000",
        "828.000",
        "830.000",
        "865.000",
        "926.000",
        "935.000",
        "950.000",
        "1.000.000",
        "1.040.000",
        "1.052.000",
        "1.110.000",
        "1.150.000",
        "1.156.000",
        "1.200.000",
        "1.210.000",
        "1.250.000",
        "1.260.000",
        "1.300.000",
        "1.385.000",
        "1.415.000",
        "1.450.000",
        "1.455.000",
        "1.500.000",
        "1.525.000",
        "1.550.000",
        "1.560.000",
        "1.600.000",
        "1.610.000",
        "1.660.000",
        "1.730.000",
        "1.733.000",
        "1.815.000",
        "1.905.000",
        "2.000.000",
        "2.075.000",
        "2.180.000",
        "2.200.000",
        "2.250.000",
        "2.258.000",
        "2.285.000",
        "2.300.000",
        "2.425.000",
        "2.500.000",
        "2.595.000",
        "2.600.000",
        "2.760.000",
        "2.770.000",
        "2.950.000",
        "3.030.000",
        "3.115.000",
        "3.150.000",
        "3.180.000",
        "3.225.000",
        "3.300.000",
        "3.465.000",
        "3.500.000",
        "3.570.000",
        "3.575.000",
        "3.805.000",
        "3.870.000",
        "4.000.000",
        "4.150.000",
        "4.330.000",
        "4.530.000",
        "4.560.000",
        "4.670.000",
        "4.700.000",
        "4.730.000",
        "5.000.000",
        "5.060.000",
        "5.190.000",
        "5.340.000",
        "5.450.000",
        "5.500.000",
        "5.540.000",
        "6.000.000",
        "6.055.000",
        "6.230.000",
        "6.500.000",
        "6.600.000",
        "6.660.000",
        "6.930.000",
        "7.000.000",
        "7.236.000",
        "7.250.000",
        "7.445.000",
        "7.580.000",
        "7.600.000",
        "7.710.000",
        "7.790.000",
        "8.000.000",
        "8.300.000",
        "8.480.000",
        "8.660.000",
        "9.000.000",
        "9.690.000",
        "10.000.000",
        "10.350.000",
        "10.380.000",
        "10.854.000",
        "10.900.000",
        "11,420.000",
        "12.000.000",
        "12.110.000",
        "12.590.000",
        "12.690.000",
        "12.950.000",
        "13.800.000",
        "13.856.000",
        "14.000.000",
        "15.000.000",
        "15.140.000",
        "15.280.000",
        "16.000.000",
        "16.110.000",
        "16.300.000",
        "16.500.000",
        "17.000.000",
        "17.310.000",
        "17.500.000",
        "18.580.000",
        "18.705.000",
        "20.000.000",
        "20.150.000",
        "20.380.000",
        "20.780.000",
        "21.000.000",
        "22.000.000",
        "23.000.000",
        "23.200.000",
        "23.500.000",
        "24.000.000",
        "24.556.000",
        "25.000.000",
        "2.760.000",
        "30.000.000",
        "20.100.000",
        "31.055.000",
        "31.500.000",
        "32.000.000",
        "32.100.000",
        "35.000.000",
        "37.000.000",
        "40.000.000",
        "45.000.000",
        "50.000.000",
        "54.000.000",
        "55.000.000",
        "58.800.000",
        "60.000.000",
        "65.000.000",
        "70.000.000",
        "76.000.000",
        "78.000.000",
        "95.000.000",
        "100.000.000",
        "120.000.000",
        "130.000.000",
        "145.000.000",
        "165.000.000",
        "180.000.000",
        "200.000.000",
        "Custom"
    };

    public static String [] keterangan = {"Pasang Baru",
            "Perubahan Daya", "Multiguna"
//            , "Premium",  "Lain - Lain"
    };

    public static String [] keterangan_new = {
            "Pasang Baru", "Multiguna"
    };

    public static String [] premium = {
            "Gold", "Silver", "Bronze", "Platinum"
    };

    public static List<Unit> units;
    public static List<Penyulang> penyulangs;
}
