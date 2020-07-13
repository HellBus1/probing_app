package com.example.phrobingapp.menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phrobingapp.Konstanta;
import com.example.phrobingapp.MainActivity;
import com.example.phrobingapp.R;
import com.example.phrobingapp.SharedPreferenceManager;
import com.example.phrobingapp.connection.ApiInterface;
import com.example.phrobingapp.connection.RetrofitBuilder;
import com.example.phrobingapp.databinding.ActivityInputBinding;
import com.example.phrobingapp.login_serv.Data;
import com.example.phrobingapp.login_serv.Login_pojo;
import com.example.phrobingapp.login_serv.Penyulang;
import com.example.phrobingapp.login_serv.Unit;
import com.example.phrobingapp.map.OsmMap;
import com.example.phrobingapp.pojo.PelangganSerializable;
import com.example.phrobingapp.pojo.SubmitData;
import com.example.phrobingapp.tools.CameraUtils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;

public class Input extends AppCompatActivity {
    public static String KEY_PINDAH = "cobas";
    ActivityInputBinding activityInputBinding;
    private static TextView temporary;
    public int year = -1;
    public int month = -1;
    public int dayOfMonth = -1;
    private int GALLERY = 1, CAMERA = 2;
    private Bitmap saved;
    private static final String ROOT_IMAGE_DIRECTORY = "/foto_bukti";
    public static int LOCATION_CODE = 10;
    private static String imageStoragePath;
    PelangganSerializable dene;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInputBinding = DataBindingUtil.setContentView(this, R.layout.activity_input);
        Intent i = getIntent();
        dene = (PelangganSerializable) i.getSerializableExtra(KEY_PINDAH);
        activityInputBinding.idPelanggan.setText(String.valueOf(dene.getPelangganId()));
        activityInputBinding.namaPelanggan.setText(dene.getNamaUsaha());
        activityInputBinding.alamatPelanggan.setText(dene.getAlamat());

        temporary = findViewById(R.id.tanggal);
        adjustEditText();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void adjustEditText(){
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Konstanta.keterangan);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Konstanta.premium);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Konstanta.tarifs);
        final ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Konstanta.multigunas);

        activityInputBinding.tarifM.setAdapter(adapter4);
        activityInputBinding.tarifM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        activityInputBinding.listKeterangan.setAdapter(adapter);
        activityInputBinding.listKeterangan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(adapter.getItem(position).equals("Premium")){
                    activityInputBinding.dropPremium.setVisibility(View.VISIBLE);
                }else{
                    activityInputBinding.dropPremium.setVisibility(View.GONE);
                }

                if(adapter.getItem(position).equals("Lain - Lain")){
                    activityInputBinding.lainLain.setVisibility(View.VISIBLE);
                }else{
                    activityInputBinding.lainLain.setVisibility(View.GONE);
                }

                if(adapter.getItem(position).equals("Multiguna")){
                    activityInputBinding.tarifM.setVisibility(View.VISIBLE);
                    activityInputBinding.tarif12.setVisibility(View.GONE);
                }else{
                    activityInputBinding.tarifM.setVisibility(View.GONE);
                    activityInputBinding.tarif12.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        activityInputBinding.listPremium.setAdapter(adapter1);
        activityInputBinding.listPremium.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Input.this, adapter1.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        activityInputBinding.tarif12.setAdapter(adapter2);
        activityInputBinding.tarif12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Konstanta.dayas);
        activityInputBinding.daya12.setAdapter(adapter3);
        activityInputBinding.daya12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        temporary.setOnFocusChangeListener((v, hasFocus) -> showDatePickerDialog(v));
        temporary.setOnClickListener(this::showDatePickerDialog);
        activityInputBinding.sho2Gambar.setOnClickListener((v) -> {
            requestMultiplePermissions();
        });
        activityInputBinding.shoLokasi12.setOnClickListener((v) ->{
            requestLocation();
        });
        activityInputBinding.submitData.setOnClickListener((v) -> {
            sendToServer();
        });
    }

    private void sendToServer(){
        String idPelanggan = activityInputBinding.idPelanggan.getText().toString();
        String namaUsaha = activityInputBinding.namaPelanggan.getText().toString();
        String alamatUsaha = activityInputBinding.alamatPelanggan.getText().toString();
        String keterangan = activityInputBinding.listKeterangan.getSelectedItem().toString();
        String kombi = "";
        if(keterangan.equals("Premium")){
            kombi = ("Premium," + activityInputBinding.listPremium.getSelectedItem().toString());
        }else if(keterangan.equals("Lain - Lain")){
            kombi = activityInputBinding.ketLain.getText().toString();
        }else{
            kombi = keterangan;
        }
        String tarif;
        if(keterangan.equals("Multiguna")){
            tarif = activityInputBinding.tarifM.getSelectedItem().toString();
        }else{
            tarif = activityInputBinding.tarif12.getSelectedItem().toString();
        }
        String daya = activityInputBinding.daya12.getSelectedItem().toString();
        String tanggal = temporary.getText().toString();
        String path = activityInputBinding.pathGambar.getText().toString();
        String lokasi = activityInputBinding.lokasi12.getText().toString();
        String keteranganTambahan = activityInputBinding.keterangant1.getText().toString();

        File file = new File(Objects.requireNonNull(Uri.parse(path).getPath()));
        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part foto = MultipartBody.Part.createFormData("foto", file.getName(), mFile);;
        RequestBody nama_usaha = RequestBody.create(MediaType.parse("text/plain"), namaUsaha);
        RequestBody alamat = RequestBody.create(MediaType.parse("text/plain"), alamatUsaha);
        RequestBody nomor_telp = RequestBody.create(MediaType.parse("text/plain"), dene.getTelefon());
        RequestBody cp = RequestBody.create(MediaType.parse("text/plain"), dene.getContactPerson());
        RequestBody fax = RequestBody.create(MediaType.parse("text/plain"), dene.getFax());
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), dene.getEmail());
        RequestBody latlong = RequestBody.create(MediaType.parse("text/plain"), lokasi);
        RequestBody keteterangantam = RequestBody.create(MediaType.parse("text/plain"), keteranganTambahan);
        RequestBody tanggalan = RequestBody.create(MediaType.parse("text/plain"), new java.sql.Date(getTanggal(tanggal).getTime()).toString());
        RequestBody keterangans = RequestBody.create(MediaType.parse("text/plain"), kombi);
        RequestBody tarrifs = RequestBody.create(MediaType.parse("text/plain"), tarif);
        RequestBody dayas = RequestBody.create(MediaType.parse("text/plain"), daya);
        RequestBody idPel = RequestBody.create(MediaType.parse("text/plain"), idPelanggan);
        RequestBody rekapId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(dene.getRekapId()));
        activityInputBinding.loadingKirim.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RetrofitBuilder.getClient().create(ApiInterface.class);
        Call<SubmitData> caller = apiInterface.update_data(
                foto, idPel, tarrifs, dayas, nama_usaha, alamat, tanggalan, keterangans, latlong, keteterangantam, rekapId
        );
        caller.enqueue(new Callback<SubmitData>() {
            @Override
            public void onResponse(Call<SubmitData> call, Response<SubmitData> response) {
                if (response.isSuccessful()){
                    try{
                        SubmitData subs = response.body();
                        if(subs.getSuccess() && subs.getPesan().equals("berhasil")){
                            activityInputBinding.loadingKirim.setVisibility(View.GONE);
                            startActivity(new Intent(Input.this, MainActivity.class));
                            finish();
//                            sendimage(path, namaUsaha, alamatUsaha,
//                                    dene.getTelefon(), dene.getContactPerson(), dene.getFax(), dene.getEmail(),
//                                    lokasi, idPelanggan, "2");
                        }else{
                            activityInputBinding.loadingKirim.setVisibility(View.GONE);
                            Toast.makeText(Input.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    activityInputBinding.loadingKirim.setVisibility(View.GONE);
                    Toast.makeText(Input.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubmitData> call, Throwable t) {
                activityInputBinding.loadingKirim.setVisibility(View.GONE);
                Log.e("Kesalahan ", t.getMessage());
                Toast.makeText(Input.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    private Date getTanggal(String tanggal){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date inputDate = null;
        try {
            inputDate = fmt.parse(tanggal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inputDate;
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog  = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Menu Pilihan");
        String [] pictureDialogItems = {
                "Pilih dari galeri",
                "Ambil foto"
        };
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        choosePhotoFromGallery();
                        break;
                    case 1:
                        choosePhotoFromCamera();
                        break;
                }
            }
        });
        pictureDialog.show();
    }

    public void choosePhotoFromGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    public void choosePhotoFromCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = CameraUtils.getOutputMediaFile(CAMERA);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(cameraIntent, CAMERA);
    }

    private void restoreFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(ROOT_IMAGE_DIRECTORY)) {
                imageStoragePath = savedInstanceState.getString(ROOT_IMAGE_DIRECTORY);
                if (!TextUtils.isEmpty(imageStoragePath)) {
                    if (imageStoragePath.substring(imageStoragePath.lastIndexOf(".")).equals("." + "jpg")) {
                        previewCapturedImage();
                    }
                }
            }
        }
    }

    public void showDatePickerDialog(View view) {
        hideKeyboard();
        Input.DateChooser amp = new Input.DateChooser(Input.this);
        amp.show(getSupportFragmentManager(), "datePick");
        hideKeyboard();
    }

    public void hideKeyboard() {
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (manager != null) {
            View view = this.getCurrentFocus();
            if (view != null)
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            return;
        }else{
            if (requestCode == GALLERY && resultCode == RESULT_OK){
                if (data != null){
                    Uri contentUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentUri);
                        String path = saveImage(bitmap);
                        saved = bitmap;
                        Toast.makeText(this, "Gambar Tersimpan", Toast.LENGTH_SHORT).show();
                        activityInputBinding.pathGambar.setText(path);
                    }catch (IOException e){
                        e.printStackTrace();
                        Toast.makeText(this, "Gagal!", Toast.LENGTH_SHORT).show();
                    }
                }
            }else if(requestCode == CAMERA && resultCode == RESULT_OK){
                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            }else if(requestCode == LOCATION_CODE && resultCode == RESULT_OK){
                assert data != null;
                activityInputBinding.lokasi12.setText(data.getStringExtra("result"));
            }
        }
    }

    private void previewCapturedImage() {
        try {
            // hide video preview
            activityInputBinding.pathGambar.setText(imageStoragePath);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private String saveImage(Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + ROOT_IMAGE_DIRECTORY);
        if (!wallpaperDirectory.exists()){
            wallpaperDirectory.mkdirs();
        }
        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("Save Image TAF", "File Saved::--->" + f.getAbsolutePath());
            return f.getAbsolutePath();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            showPictureDialog();
//                            Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(Input.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }).onSameThread().check();
    }

    private void requestLocation(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Intent i = new Intent(Input.this, OsmMap.class);
                            OsmMap.a = false;
                            startActivityForResult(i, LOCATION_CODE);
//                            Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(Input.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }).onSameThread().check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Input.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    public static class DateChooser extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        Input loginActivity;

        DateChooser(Input activity) {
            this.loginActivity = activity;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(MONTH);
            int dayOfMonth = calendar.get(DAY_OF_MONTH);

            //Initialize a new DatePickerDialog
            DatePickerDialog date = new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, dayOfMonth);
            return date;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if ((month + 1) < 10) {
                temporary.setText(year + "-" + "0" + (month + 1) + "-" + dayOfMonth);
            } else {
                temporary.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
            loginActivity.hideKeyboard();
            loginActivity.year = year;
            loginActivity.month = month;
            loginActivity.dayOfMonth = dayOfMonth;
        }

        @Override
        public void onDismiss(@NonNull DialogInterface dialog) {
            super.onDismiss(dialog);
            loginActivity.hideKeyboard();
        }

        @Override
        public void onCancel(@NonNull DialogInterface dialog) {
            super.onCancel(dialog);
            loginActivity.hideKeyboard();
        }
    }
}
