package com.ag.bta.utils.imageutils.compressor;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.ag.bta.main.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

abstract public class CapturePhoto extends AppCompatActivity {
    private String cameraImagePath = null;
    private static final int REQUEST_TAKE_PHOTO = 113;
    private AppCompatActivity activity = CapturePhoto.this;

abstract protected void   startCompression(AppCompatActivity activity, String file);

    protected void dispatchTakePictureIntent() {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            // Ensure that there's a camera activity to handle the intent
                            if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                                // Create the File where the photo should go
                                File newFile = null;
                                try {
                                    newFile = createImageFile();
                                    cameraImagePath = newFile.getAbsolutePath();
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        cameraImagePath = newFile.getAbsolutePath();
                                        takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                        Uri photoURI = FileProvider.getUriForFile(activity, "com.ag.bta.ui.main.fileprovider", newFile);
                                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                    } else {
                                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
                                    }
                                    activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                Toast.makeText(activity, R.string.no_camera_exists, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(activity, R.string.permission_request_denied, Toast.LENGTH_LONG)
                                    .show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = "JPEG_" + System.currentTimeMillis() + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        if (!storageDir.exists()) {
            if (!storageDir.mkdir()) {
                throw new IOException();
            }
        }
        return new File(storageDir, imageFileName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK && cameraImagePath != null && !TextUtils.isEmpty(cameraImagePath)) {
                    File mFile = new File(cameraImagePath);
                    if (mFile != null && mFile.exists()) {
                    //
                        startCompression(activity, mFile.getAbsolutePath());
                    }
                    cameraImagePath = null;
                }
                break;
        }
    }
}
