package com.example.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;
import android.content.Intent;
import android.widget.Toast;
import android.os.Handler;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    Button openFileBtn, openUrlBtn, playBtn, pauseBtn, stopBtn, restartBtn;
    VideoView videoView;
    EditText urlInput;
    SeekBar progressBar;

    Handler handler = new Handler();
    Runnable runnable;

    Uri mediaUri;

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
        progressBar = findViewById(R.id.progressBar);

        videoView = findViewById(R.id.videoView);
        urlInput = findViewById(R.id.urlInput);

        openFileBtn.setOnClickListener(v -> openFile());
        openUrlBtn.setOnClickListener(v -> openUrl());

        playBtn.setOnClickListener(v -> {
            videoView.start();
            updateProgress();
        });

        pauseBtn.setOnClickListener(v -> videoView.pause());

        stopBtn.setOnClickListener(v -> {
            videoView.stopPlayback();
            progressBar.setProgress(0);
        });

        restartBtn.setOnClickListener(v -> {
            videoView.seekTo(0);
            videoView.start();
        });

        // Seek bar dragging
        progressBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            videoView.seekTo(progress);
                        }
                    }

                    @Override public void onStartTrackingTouch(SeekBar seekBar) {}
                    @Override public void onStopTrackingTouch(SeekBar seekBar) {}
                });
    }

    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/* video/*");
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

    private void updateProgress() {

        videoView.setOnPreparedListener(mp -> {

            progressBar.setMax(videoView.getDuration());

            runnable = new Runnable() {
                @Override
                public void run() {
                    progressBar.setProgress(videoView.getCurrentPosition());
                    handler.postDelayed(this, 500);
                }
            };

            handler.postDelayed(runnable, 0);
        });
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