package com.example.cameragalleryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Date;

public class ImageDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView imageInfo;
    View deleteBtn;

    String path;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        imageView = findViewById(R.id.imageView);
        imageInfo = findViewById(R.id.imageInfo);
        deleteBtn = findViewById(R.id.deleteBtn);

        findViewById(R.id.backBtn).setOnClickListener(v -> finish());

        path = getIntent().getStringExtra("path");
        file = new File(path);

        imageView.setImageBitmap(BitmapFactory.decodeFile(path));

        String info =
                "Name: " + file.getName() +
                        "\nPath: " + path +
                        "\nSize: " + file.length()/1024 + " KB" +
                        "\nType: " + file.getName().substring(file.getName().lastIndexOf(".")) +
                        "\nDate: " + new Date(file.lastModified());

        imageInfo.setText(info);

        deleteBtn.setOnClickListener(v -> deleteImage());
    }

    private void deleteImage() {

        new AlertDialog.Builder(this)
                .setTitle("Delete Image")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (d,w) -> {

                    file.delete();
                    Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();
                    finish();

                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}