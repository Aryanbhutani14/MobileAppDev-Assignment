package com.example.cameragalleryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        deleteBtn.setOnClickListener(v -> showDeleteDialog());
    }

    private void showDeleteDialog(){

        new AlertDialog.Builder(this)
                .setTitle("Delete Image")
                .setMessage("Are you sure you want to delete this image?")
                .setPositiveButton("Yes", (dialog, which) -> {

                    Toast.makeText(this,
                            "Image deleted",
                            Toast.LENGTH_SHORT).show();

                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}