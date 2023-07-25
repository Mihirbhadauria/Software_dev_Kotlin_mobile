package swinburne.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {

    private var goalMediaPlayer: MediaPlayer? = null
    private var stealMediaPlayer: MediaPlayer? = null
    private var resetMediaPlayer: MediaPlayer? = null
    private var score: Int = 0
    private lateinit var scoreDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_layout)

        scoreDisplay = findViewById(R.id.ScoreDisplay)
        score = savedInstanceState?.getInt("SCORE_KEY") ?: 0
        scoreDisplay.text = getString(R.string.score, score)
    }

    override fun onResume() {
        super.onResume()

        goalMediaPlayer = MediaPlayer.create(this, R.raw.woodpecker)
        stealMediaPlayer = MediaPlayer.create(this, R.raw.goofy_spring_bounces)
        resetMediaPlayer = MediaPlayer.create(this, R.raw.reset)

        val goalButton: Button? = findViewById(R.id.Goal)
        goalButton?.setOnClickListener {
            goalMediaPlayer?.start()
            if (score < 15) {
                score++
            }
            scoreDisplay.text = getString(R.string.score, score)
        }

        val stealButton: Button? = findViewById(R.id.Steal)
        stealButton?.setOnClickListener {
            stealMediaPlayer?.start()
            if (score > 0) {
                score--
            }
            scoreDisplay.text = getString(R.string.score, score)
        }

        val resetButton: Button? = findViewById(R.id.Reset)
        resetButton?.setOnClickListener {
            resetMediaPlayer?.start()
            score = 0
            scoreDisplay.text = getString(R.string.score, score)
        }
    }

    override fun onPause() {
        super.onPause()
        goalMediaPlayer?.release()
        stealMediaPlayer?.release()
        resetMediaPlayer?.release()

        goalMediaPlayer = null
        stealMediaPlayer = null
        resetMediaPlayer = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SCORE_KEY", score)
    }
}
