package com.example.cameragalleryapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import java.io.File;
import java.util.ArrayList;
import android.os.Environment;

public class GalleryActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<File> imageFiles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = findViewById(R.id.gridView);
        findViewById(R.id.backBtn).setOnClickListener(v -> finish());

        loadImages();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(GalleryActivity.this, FileImageAdapter.class);
                i.putExtra("path", imageFiles.get(position).getAbsolutePath());
                startActivity(i);
            }
        });
    }

    private void loadImages(){

        File folder = new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES),
                "CameraGalleryApp");

        File[] files = folder.listFiles();

        if(files != null){
            for(File f : files){
                imageFiles.add(f);
            }
        }

        gridView.setAdapter(new FileImageAdapter(this,imageFiles));
    }
}