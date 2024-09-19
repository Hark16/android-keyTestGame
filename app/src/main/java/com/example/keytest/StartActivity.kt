package com.example.keytest

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        mediaPlayer = MediaPlayer.create(this, R.raw.start)
        mediaPlayer?.start()

        val closeButton: Button = findViewById(R.id.startButton)
        closeButton.setOnClickListener {
            val intent = Intent(this@StartActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val closeButton1: Button = findViewById(R.id.closeButton)
        closeButton1.setOnClickListener {

            finish()
        }


    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer?.release()
        mediaPlayer = null
    }

}