package com.example.keytest

import android.os.Handler
import android.media.MediaPlayer
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mediaPlayer = MediaPlayer.create(this, R.raw.key)
        mediaPlayer?.start()


        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer?.release()
        mediaPlayer = null
    }

}