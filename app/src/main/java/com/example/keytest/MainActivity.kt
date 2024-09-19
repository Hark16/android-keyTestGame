package com.example.keytest


import android.content.Intent
import android.media.MediaPlayer
import android.widget.Button
import android.widget.Toast
import android.util.Log
import android.os.Handler
import android.widget.TextView
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private var mediaPlayer: MediaPlayer? = null
    private var mediaPlayer2: MediaPlayer? = null
    private var mediaPlayer3: MediaPlayer? = null


    private lateinit var timerHandler: Handler
    private lateinit var timerTextView: TextView
    private lateinit var englishTextView: TextView
    private lateinit var scoreTextView: TextView

    val TAG = "MainActivity"

    private var secondsRemaining = 1 // Initial time in seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize MediaPlayer with the audio file
        mediaPlayer = MediaPlayer.create(this, R.raw.clock)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.point)
        mediaPlayer3 = MediaPlayer.create(this, R.raw.wrong)


        // Set looping to true to play the audio in a loop
        mediaPlayer?.isLooping = true

        // Start playing the audio
        mediaPlayer?.start()

        val closeButton: Button = findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            finish() // Close the activity (and the app)
        }

        timerTextView = findViewById(R.id.timer)
        englishTextView = findViewById(R.id.english)
        // Find EditText by ID
        editText = findViewById(R.id.editText)
        scoreTextView = findViewById(R.id.score)
        var score = 0


        var randomChar = generateRandomCapitalChar()
        var value1 = randomChar.toString()
        englishTextView.text = value1 // Update text view here
        englishTextView.requestFocus()


        timerHandler = Handler()

        val timerRunnable = object : Runnable {
            override fun run() {
                timerTextView.text = "Timer : "+ secondsRemaining.toString()



                if(secondsRemaining == 10){
secondsRemaining = 1
                    var value = editText.text.toString()


                    if (value1 == value) {
                        score++
                        scoreTextView.text = "Score : " + score
                        mediaPlayer2?.start()

                        if (score == 10) {
                            val intent = Intent(this@MainActivity, MainActivity2::class.java)
                            startActivity(intent)
                            finish()


                        }
                    } else {
                        score--
                        scoreTextView.text = "Score : " + score

                        Log.d(TAG, "value: $value, value1: $value1")
                        mediaPlayer3?.start()

                    }
                    randomChar = generateRandomCapitalChar()
                    value1 = randomChar.toString()
                    englishTextView.text = value1 // Update text view here
                    englishTextView.requestFocus()

                    editText.setText("") // Clears the text in the EditText

                }
                else{
                    secondsRemaining++

                }

                timerHandler.postDelayed(this, 1100) // Run the Runnable again after 1 second (1000 milliseconds)
            }
        }

        timerHandler.post(timerRunnable)

        // Show the soft keyboard
// Show the soft keyboard after a delay
        editText.postDelayed({
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }, 200) // Delay of 200 milliseconds

    }

    private fun generateRandomCapitalChar(): Char {
        val uppercaseStart = 'a'.toInt()
        val uppercaseEnd = 'z'.toInt()
        val randomAscii = (uppercaseStart..uppercaseEnd).random()
        return randomAscii.toChar()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove any remaining callbacks to prevent memory leaks
        timerHandler.removeCallbacksAndMessages(null)

        // Release the MediaPlayer when the activity is destroyed
        mediaPlayer?.release()
        mediaPlayer = null
        mediaPlayer2?.release()
        mediaPlayer2 = null
        mediaPlayer3?.release()
        mediaPlayer3 = null


    }
}
