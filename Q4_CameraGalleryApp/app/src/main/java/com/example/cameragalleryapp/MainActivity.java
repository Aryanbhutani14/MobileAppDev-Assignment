package com.example.cameragalleryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button captureBtn, openGalleryBtn;
    GridView gridView;

    ArrayList<Bitmap> images = new ArrayList<>();
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        captureBtn = findViewById(R.id.captureBtn);
        openGalleryBtn = findViewById(R.id.openGalleryBtn);
        gridView = findViewById(R.id.gridView);

        adapter = new ImageAdapter(this, images);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(MainActivity.this,
                    ImageDetailsActivity.class);

            intent.putExtra("image", images.get(position));

            startActivity(intent);
        });

        captureBtn.setOnClickListener(v -> openCamera());
    }

    private void openCamera(){

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    100);
            return;
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==1 && resultCode==RESULT_OK){
            Bitmap photo = (Bitmap)data.getExtras().get("data");
            images.add(photo);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100){
            if(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                openCamera();
            }
        }
    }
}