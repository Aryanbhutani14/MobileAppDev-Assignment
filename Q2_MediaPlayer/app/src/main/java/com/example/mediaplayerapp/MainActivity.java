package com.example.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;
import android.media.MediaPlayer;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button openFileBtn, openUrlBtn, playBtn, pauseBtn, stopBtn, restartBtn;
    VideoView videoView;
    EditText urlInput;

    Uri mediaUri;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFileBtn = findViewById(R.id.openFileBtn);
        openUrlBtn = findViewById(R.id.openUrlBtn);
        playBtn = findViewById(R.id.playBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        stopBtn = findViewById(R.id.stopBtn);
        restartBtn = findViewById(R.id.restartBtn);

        videoView = findViewById(R.id.videoView);
        urlInput = findViewById(R.id.urlInput);

        openFileBtn.setOnClickListener(v -> openFile());

        openUrlBtn.setOnClickListener(v -> openUrl());

        playBtn.setOnClickListener(v -> play());

        pauseBtn.setOnClickListener(v -> pause());

        stopBtn.setOnClickListener(v -> stop());

        restartBtn.setOnClickListener(v -> restart());
    }

    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(intent, 1);
    }

    private void openUrl() {

        String url = urlInput.getText().toString();

        if(url.contains("youtube.com")){
            Toast.makeText(this,
                    "YouTube links not supported",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        mediaUri = Uri.parse(url);
        videoView.setVideoURI(mediaUri);
    }

    private void play() {
        videoView.start();
    }

    private void pause() {
        videoView.pause();
    }

    private void stop() {
        videoView.stopPlayback();
    }

    private void restart() {
        videoView.seekTo(0);
        videoView.start();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            mediaUri = data.getData();
            videoView.setVideoURI(mediaUri);
        }
    }
}