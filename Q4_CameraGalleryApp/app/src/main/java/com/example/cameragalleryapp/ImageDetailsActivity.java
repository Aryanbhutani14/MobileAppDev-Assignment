package com.example.cameragalleryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView imageInfo;
    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        imageView = findViewById(R.id.imageView);
        imageInfo = findViewById(R.id.imageInfo);
        deleteBtn = findViewById(R.id.deleteBtn);

        Bitmap bitmap = getIntent().getParcelableExtra("image");

        imageView.setImageBitmap(bitmap);

        imageInfo.setText("Captured Image");

        deleteBtn.setOnClickListener(v -> finish());
    }
}