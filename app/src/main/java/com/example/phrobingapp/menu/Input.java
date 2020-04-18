package com.example.phrobingapp.menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phrobingapp.R;
import com.example.phrobingapp.databinding.ActivityInputBinding;
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
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;

public class Input extends AppCompatActivity {

    ActivityInputBinding activityInputBinding;
    private static TextView temporary;
    public int year = -1;
    public int month = -1;
    public int dayOfMonth = -1;
    private int GALLERY = 1, CAMERA = 2;
    private Bitmap saved;
    private static final String ROOT_IMAGE_DIRECTORY = "/foto_bukti";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInputBinding = DataBindingUtil.setContentView(this, R.layout.activity_input);
        temporary = findViewById(R.id.tanggal);
        adjustEditText();
    }

    private void adjustEditText(){
        // tanggal lahir
        temporary.setOnFocusChangeListener((v, hasFocus) -> showDatePickerDialog(v));
        temporary.setOnClickListener(this::showDatePickerDialog);
        activityInputBinding.sho2Gambar.setOnClickListener((v) -> {showPictureDialog();});
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
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    public void choosePhotoFromCamera(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA);
    }

    public void showDatePickerDialog(View view) {
        hideKeyboard();
        DateChooser amp = new DateChooser(this);
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
        if (resultCode == this.RESULT_CANCELED){
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
            }
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
                            Toast.makeText(getApplicationContext(), "Semua permintaan telah diizinkan", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
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

    public static class DateChooser extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        Input loginActivity;

        DateChooser(Input activity) {
            this.loginActivity = activity;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = 2004;
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
