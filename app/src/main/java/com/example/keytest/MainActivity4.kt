package com.example.keytest

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity4 : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        mediaPlayer = MediaPlayer.create(this, R.raw.cross)
        mediaPlayer?.start()

        val closeButton: Button = findViewById(R.id.nextButton)
        closeButton.setOnClickListener {
            val intent = Intent(this@MainActivity4, MainActivity5::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer?.release()
        mediaPlayer = null
    }

}