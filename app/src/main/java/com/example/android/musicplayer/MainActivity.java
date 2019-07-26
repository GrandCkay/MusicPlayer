package com.example.android.musicplayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.security.AccessController;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageButton imageButtonArrow;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.babek);

        imageButtonArrow = findViewById(R.id.imageButtonArrow);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());      // устанавливаем максимум равный длительности трека (getDuration - возвращает длительность аудио трека)


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {  // установка позиции секбара польователем

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            });


        new Timer().scheduleAtFixedRate(new TimerTask() {  // создаем новый метод класса Тaймер и устанавливаем новое задание (ТаймерТеск)

            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition()); // устанавливаем секбар в текущую позицию аудиофайла
            }
        }, 0, 1000);  // 0 - (задержка) через какое время запустить таймер; 10000 - периодичность


    int color = ContextCompat.getColor(getBaseContext(), R.color.myColor);
    seekBar = findViewById(R.id.seekBar);
    seekBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_ATOP); // полоска
    seekBar.getThumb().setColorFilter(color, PorterDuff.Mode.SRC_ATOP); // кругляшок


    }

    public void previous(View view) {


    }

    public void arrow(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            imageButtonArrow.setImageResource(R.drawable.ic_play_arrow_beige_24dp);
        } else {
            mediaPlayer.start();
            imageButtonArrow.setImageResource(R.drawable.ic_pause_beige_24dp);
        }
    }

    public void next(View view) {

        }


    }
