package com.ag.bta.utils.imageutils.compressor;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import java.io.File;

import  com.ag.bta.main.R;
public class TestCompressionActivity extends CapturePhoto {


    private AppCompatImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.atest_compress_image);
        imageView = findViewById(R.id.imgView);
        findViewById(R.id.btn_capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    @Override
    protected void startCompression(AppCompatActivity activity, String filePath ) {
        new ImageCompressor.Builder(activity)
                .setConfiguration(ImageConfiguration.MEDIA_QUALITY_LOW)
                .setImage(filePath)
                //.setImageOutputSize(2000.0f, 1000.0f) //it will override configuration params
                .onImageCompressed(new ImageCompressListener() {
                    @Override
                    public void onImageCompressed(byte[] bytes, File file) {
                        if(file != null && file.exists()) {
                            imageView.setImageURI(Uri.parse(file.getAbsolutePath()));
                        }
                    }
                })
                .build();
    }









}
