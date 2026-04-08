package com.example.cameragalleryapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    View selectFolder, takePhoto, openGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        selectFolder = findViewById(R.id.selectFolderBtn);
        takePhoto = findViewById(R.id.takePhotoBtn);
        openGallery = findViewById(R.id.openGalleryBtn);

        selectFolder.setOnClickListener(v ->
                startActivity(new Intent(this, FolderActivity.class)));

        takePhoto.setOnClickListener(v ->
                startActivity(new Intent(this, CameraActivity.class)));

        openGallery.setOnClickListener(v ->
                startActivity(new Intent(this, GalleryActivity.class)));
    }
}