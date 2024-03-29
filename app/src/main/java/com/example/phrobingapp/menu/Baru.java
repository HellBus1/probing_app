
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
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
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
import com.example.phrobingapp.connection.ApiInterface;
import com.example.phrobingapp.connection.RetrofitBuilder;
import com.example.phrobingapp.databinding.ActivityInput2Binding;
import com.example.phrobingapp.login_serv.Penyulang;
import com.example.phrobingapp.login_serv.Unit;
import com.example.phrobingapp.map.OsmMap;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;

public class Baru extends AppCompatActivity implements View.OnClickListener {
    private ActivityInput2Binding binding;
    public int year = -1;
    public int month = -1;
    public int dayOfMonth = -1;
    public static int GALLERY = 1, CAMERA = 2;
    public static final String ROOT_IMAGE_DIRECTORY = "/foto_bukti";
    public static TextView temporary;
    private static String imageStoragePath;
    Bitmap saved;
    public static int LOCATION_CODE = 10;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_input2);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init(){
        final ArrayAdapter<String> adapterTarif = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Konstanta.tarifs);
        final ArrayAdapter<String> adapterDaya = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Konstanta.dayas);
        final ArrayAdapter<String> adapterIndustri = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Konstanta.tipeIndustri);
        final ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Konstanta.keterangan_new);
        final ArrayAdapter<String> adapterMulti = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Konstanta.multigunas);

        List<String> unitss = new ArrayList<>();
        for(Unit a : Konstanta.units){
            unitss.add(a.getUlp());
        }
        final ArrayAdapter<String> adapterUnits = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, unitss);

        List<String> penyulangs = new ArrayList<>();
        for(Penyulang a : Konstanta.penyulangs){
            penyulangs.add(a.getNamaPenyulang());
        }
        final ArrayAdapter<String> adapterPenyulang = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, penyulangs);

        binding.tarif1.setAdapter(adapterTarif);
        binding.tarif1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
//                Toast.makeText(Input2.this, "Selected "+ adapterTarif.getItem(i),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.tarif112.setAdapter(adapterMulti);

        binding.listKeterangan2.setAdapter(adapter4);
        binding.listKeterangan2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(adapter4.getItem(position).equals("Pasang Baru")){
                    binding.kumpulanDaya1.setVisibility(View.VISIBLE);
                    binding.kumpulanDaya2.setVisibility(View.GONE);
                    binding.tarifBaru.setVisibility(View.VISIBLE);
                    binding.tarifMulti.setVisibility(View.GONE);
                }else{
                    binding.kumpulanDaya1.setVisibility(View.GONE);
                    binding.kumpulanDaya2.setVisibility(View.VISIBLE);
                    binding.tarifBaru.setVisibility(View.GONE);
                    binding.tarifMulti.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.daya1.setAdapter(adapterDaya);
        binding.daya1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int positiion, long l) {
                if(adapterDaya.getItem(positiion).equals("Custom")){
                    binding.containerCustom.setVisibility(View.VISIBLE);
                }else{
                    binding.containerCustom.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.unit1.setAdapter(adapterUnits);
//        binding.penyulang1.setAdapter(adapterPenyulang);
//        binding.industri1.setAdapter(adapterIndustri);

        temporary = findViewById(R.id.tanggal1);
        temporary.setOnFocusChangeListener((v, hasFocus) -> showDatePickerDialog(v));
        temporary.setOnClickListener(this::showDatePickerDialog);
        binding.sho2Gambar1.setOnClickListener((v) -> {
            requestMultiplePermissions();
        });
        binding.shoLokasi.setOnClickListener(this);
        binding.inputDatas.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(ROOT_IMAGE_DIRECTORY, imageStoragePath);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        imageStoragePath = savedInstanceState.getString(ROOT_IMAGE_DIRECTORY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inputDatas:{
                if(checkAllnotEmpty()){
                    binding.loadingSatu.setVisibility(View.VISIBLE);
                    int kode_unit = 0;
                    int kode_penyulang = 0;
                    for(Unit units : Konstanta.units){
                        if(units.getUlp().equals(binding.unit1.getSelectedItem().toString())){
                            kode_unit = units.getId();
                            break;
                        }
                    }
//                    for(Penyulang penyulang : Konstanta.penyulangs){
//                        if(penyulang.getNamaPenyulang().equals(binding.penyulang1.getSelectedItem().toString())){
//                            kode_penyulang = penyulang.getId();
//                            break;
//                        }
//                    }

                    File file = new File(Objects.requireNonNull(Uri.parse(binding.pathGambar2.getText().toString()).getPath()));
                    String namaPelanggan = binding.namaPelanggan1.getText().toString();
                    String alamatPelanggan = binding.alamatPelanggan1.getText().toString();
                    String telepons = binding.telp.getText().toString();
                    String contact = binding.cp.getText().toString();
                    String faximili = binding.fax.getText().toString();
                    String emails = binding.email.getText().toString();
                    String lokasis = binding.lokasi1.getText().toString();
//                    String jenis_usaha = binding.jenisUsaha.getText().toString();
//                    String industri = binding.industri1.getSelectedItem().toString();
                    String jenis_usaha = "temp";
                    String industri = "temp";
                    String keterangans = binding.listKeterangan2.getSelectedItem().toString();
                    String tarifs = "";
                    String dayas = "";
                    if(keterangans.equals("Pasang Baru")) {
                        dayas = binding.daya1.getSelectedItem().toString();
                        if(dayas.equals("Custom")){
                            dayas = binding.customDaya.getText().toString();
                        }
                        tarifs = binding.tarif1.getSelectedItem().toString();
                    }else{
                        dayas = binding.daya25.getText().toString();
                        tarifs = binding.tarif112.getSelectedItem().toString();
                    }
                    String tanggalan = new java.sql.Date(getTanggal(temporary.getText().toString()).getTime()).toString();
                    String keterangant = binding.keterangant1.getText().toString();


                    RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Part foto = MultipartBody.Part.createFormData("foto", file.getName(), mFile);

                    RequestBody latlong = RequestBody.create(MediaType.parse("text/plain"), lokasis);
                    RequestBody nama_usaha = RequestBody.create(MediaType.parse("text/plain"), namaPelanggan);
                    RequestBody alamat = RequestBody.create(MediaType.parse("text/plain"), alamatPelanggan);
                    RequestBody nomor_telp = RequestBody.create(MediaType.parse("text/plain"), telepons);
                    RequestBody cp = RequestBody.create(MediaType.parse("text/plain"), contact);
                    RequestBody fax = RequestBody.create(MediaType.parse("text/plain"), faximili);
                    RequestBody email = RequestBody.create(MediaType.parse("text/plain"), emails);
                    RequestBody id = RequestBody.create(MediaType.parse("text/plain"), "1");
                    RequestBody flag = RequestBody.create(MediaType.parse("text/plain"), "1");

                    RequestBody unit = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(kode_unit));
                    RequestBody penyulang = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(kode_penyulang));
                    RequestBody jenisUsaha = RequestBody.create(MediaType.parse("text/plain"), jenis_usaha);
                    RequestBody tipeIndustri = RequestBody.create(MediaType.parse("text/plain"), industri);
                    RequestBody keterangan1 = RequestBody.create(MediaType.parse("text/plain"), keterangans);
                    RequestBody tarif1 = RequestBody.create(MediaType.parse("text/plain"), tarifs);
                    RequestBody daya1 = RequestBody.create(MediaType.parse("text/plain"), dayas);
                    RequestBody tanggalan1 = RequestBody.create(MediaType.parse("text/plain"), tanggalan);
                    RequestBody keterangant1 = RequestBody.create(MediaType.parse("text/plain"), keterangant);

                    ApiInterface apiInterface = RetrofitBuilder.getClient().create(ApiInterface.class);
                    Call<SubmitData> caller = apiInterface.post_data(
                            unit,
                            foto,
                            penyulang,
                            nama_usaha,
                            alamat,
                            nomor_telp,
                            cp,
                            fax,
                            email,
                            jenisUsaha,
                            tipeIndustri,
                            keterangan1,
                            tarif1,
                            daya1,
                            tanggalan1,
                            latlong,
                            keterangant1
                    );
                    caller.enqueue(new Callback<SubmitData>() {
                        @Override
                        public void onResponse(Call<SubmitData> call, Response<SubmitData> response) {
                            if (response.isSuccessful()){
                                try{
                                    SubmitData subs = response.body();
                                    if(subs.getSuccess() && subs.getPesan().equals("berhasil")){
                                        binding.loadingSatu.setVisibility(View.GONE);
                                        startActivity(new Intent(Baru.this, MainActivity.class));
                                        finish();
                                    }else{
                                        binding.loadingSatu.setVisibility(View.GONE);
                                        Toast.makeText(Baru.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }else{
                                binding.loadingSatu.setVisibility(View.GONE);
                                Toast.makeText(Baru.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SubmitData> call, Throwable t) {
                            binding.loadingSatu.setVisibility(View.GONE);
//                            Log.e("gagal ", t.getMessage());
                            Toast.makeText(Baru.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }break;
            case R.id.sho_lokasi:{
                requestLocation();
            }break;
            default:
        }
    }

    boolean checkAllnotEmpty() {
        if(!temporary.getText().toString().isEmpty() && !binding.pathGambar2.getText().toString().isEmpty()){
            return true;
        }

        return false;
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
        Baru.DateChooser amp = new Baru.DateChooser(Baru.this);
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
                        binding.pathGambar2.setText(path);
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
                binding.lokasi1.setText(data.getStringExtra("result"));
            }
        }
    }

    private void previewCapturedImage() {
        try {
            // hide video preview
            binding.pathGambar2.setText(imageStoragePath);
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
                        Toast.makeText(Baru.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
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
                            Intent i = new Intent(Baru.this, OsmMap.class);
                            OsmMap.a = true;
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
                        Toast.makeText(Baru.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }).onSameThread().check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Baru.this);
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

        Baru loginActivity;

        DateChooser(Baru activity) {
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
            return new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, dayOfMonth);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            if ((month + 1) < 10) {
                temporary.setText(year + "-" +  "0" + (month + 1) + "-" + dayOfMonth);
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
