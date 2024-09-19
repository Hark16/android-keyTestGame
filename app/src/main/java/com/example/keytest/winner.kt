package com.example.keytest

import android.widget.Button

import android.content.Intent

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WinnerActivity: AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

// Initialize MediaPlayer with the audio file
        mediaPlayer = MediaPlayer.create(this, R.raw.winner)

        mediaPlayer?.start()

        val restartButton: Button = findViewById(R.id.restartButton)
        restartButton.setOnClickListener {
            val intent = Intent(this@WinnerActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val closeButton: Button = findViewById(R.id.closeButton)
        closeButton.setOnClickListener {

            finish()
        }


    }

    override fun onDestroy() {
        super.onDestroy()


        // Release the MediaPlayer when the activity is destroyed
        mediaPlayer?.release()
        mediaPlayer = null

    }
}