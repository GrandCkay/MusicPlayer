package com.example.android.musicplayer;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageButton imageButtonArrow;
    SeekBar seekBar;
    boolean nextReset = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtonArrow = findViewById(R.id.imageButtonArrow);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.babek);

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
        mediaPlayer.stop();
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.prepareAsync();
        imageButtonArrow.setImageResource(R.drawable.ic_play_arrow_beige_24dp);
        nextReset = true;
    }

    public void arrow(View view) {

        TextView nameTrack = findViewById(R.id.textViewName);
        nameTrack.setText("Бабек Мамедрзаев - Принцесса");

        if (nextReset) {

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                imageButtonArrow.setImageResource(R.drawable.ic_play_arrow_beige_24dp);
            } else if (nextReset) {
                mediaPlayer.start();
                imageButtonArrow.setImageResource(R.drawable.ic_pause_beige_24dp);
            }
        }
    }

    public void next(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            seekBar.setMax(100);
            imageButtonArrow.setImageResource(R.drawable.ic_stop_beige_24dp);
            nextReset = false;
        }
    }

}