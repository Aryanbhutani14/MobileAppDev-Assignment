package com.example.cameragalleryapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

public class FolderActivity extends AppCompatActivity {

    Button chooseFolder;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        chooseFolder = findViewById(R.id.chooseFolder);
        findViewById(R.id.backBtn).setOnClickListener(v -> finish());

        chooseFolder.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
            startActivityForResult(intent,100);
        });
    }
}