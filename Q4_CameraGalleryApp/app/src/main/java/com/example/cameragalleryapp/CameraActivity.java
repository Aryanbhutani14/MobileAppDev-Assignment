package com.example.cameragalleryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import android.os.Environment;
import android.view.View;

public class CameraActivity extends AppCompatActivity {

    View captureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        captureBtn = findViewById(R.id.captureBtn);

        captureBtn.setOnClickListener(v -> openCamera());
        findViewById(R.id.backBtn).setOnClickListener(v -> finish());
    }

    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==1 && resultCode==RESULT_OK){
            Bitmap photo = (Bitmap)data.getExtras().get("data");
            saveImage(photo);
        }
    }

    private void saveImage(Bitmap bitmap) {
        try {

            File folder = new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES),
                    "CameraGalleryApp");

            if (!folder.exists()) folder.mkdirs();

            File file = new File(folder,
                    "IMG_" + System.currentTimeMillis() + ".jpg");

            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();

            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}