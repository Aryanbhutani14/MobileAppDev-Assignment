package com.example.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;
import android.widget.SeekBar;
import android.widget.ImageButton;
import android.view.View;
import android.widget.LinearLayout;

import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.common.MediaItem;
import androidx.media3.ui.PlayerView;


public class MainActivity extends AppCompatActivity {

    Button openFileBtn, openUrlBtn;
    ImageButton playBtn, pauseBtn, stopBtn, restartBtn;
    PlayerView playerView;
    EditText urlInput;
    SeekBar progressBar;
    LinearLayout controlLayout;

    ExoPlayer player;
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
        controlLayout = findViewById(R.id.controlLayout);

        playerView = findViewById(R.id.playerView);
        urlInput = findViewById(R.id.urlInput);

        player = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        openFileBtn.setOnClickListener(v -> openFile());
        openUrlBtn.setOnClickListener(v -> openUrl());

        playBtn.setOnClickListener(v -> player.play());

        pauseBtn.setOnClickListener(v -> player.pause());

        stopBtn.setOnClickListener(v -> player.stop());

        restartBtn.setOnClickListener(v -> {
            player.seekTo(0);
            player.play();
        });
    }

    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES,
                new String[]{"audio/*","video/*"});
        startActivityForResult(intent, 1);
    }

    private void openUrl() {

        String url = urlInput.getText().toString().trim();

        if(url.isEmpty()){
            Toast.makeText(this,"Enter URL first",Toast.LENGTH_SHORT).show();
            return;
        }

        mediaUri = Uri.parse(url);

        playerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        controlLayout.setVisibility(View.VISIBLE);

        MediaItem mediaItem = MediaItem.fromUri(mediaUri);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            mediaUri = data.getData();

            playerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            controlLayout.setVisibility(View.VISIBLE);

            MediaItem mediaItem = MediaItem.fromUri(mediaUri);
            player.setMediaItem(mediaItem);
            player.prepare();
            player.play();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }
}