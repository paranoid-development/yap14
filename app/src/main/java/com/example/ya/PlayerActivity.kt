package com.example.ya

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.button.MaterialButton

class PlayerActivity : AppCompatActivity() {

  companion object {
    private const val STATE_DEFAULT = 0
    private const val STATE_PREPARED = 1
    private const val STATE_PLAYING = 2
    private const val STATE_PAUSED = 3
  }

  private var playerState = STATE_DEFAULT

  private lateinit var play: Button
  private var mediaPlayer = MediaPlayer()
  private var url: String? =
    "http://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview112/v4/ac/c7/d1/acc7d13f-6634-495f-caf6-491eccb505e8/mzaf_4002676889906514534.plus.aac.p.m4a"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_player)
    play = findViewById(R.id.playButton)

    Thread {
      // при пересоздании активности будет утечка, память для this не освободится
      val x = this@PlayerActivity
      Thread.sleep(60000)
      Log.d("xxx", "$x")
    }

    // обращение к диску в UI потоке
    val sharedPref = getPreferences(Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
      putInt("someKey", 0)
      commit()
    }

    preparePlayer()

    play.setOnClickListener {
      playbackControl()
    }
    val button = findViewById<Button>(R.id.justButton)
    Glide.with(this)
      .asDrawable()
      .load("https://img.icons8.com/?size=2x&id=63807&format=png")
      .into(
        object : CustomTarget<Drawable>() {
          override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            button.setCompoundDrawablesWithIntrinsicBounds( resource, null, null, null);
          }
          override fun onLoadCleared(placeholder: Drawable?) = Unit
        },
      )
  }

  override fun onPause() {
    super.onPause()
    pausePlayer()
  }

  override fun onDestroy() {
    super.onDestroy()
    mediaPlayer.release()
  }

  private fun playbackControl() {
    when (playerState) {
      STATE_PLAYING -> {
        pausePlayer()
      }
      STATE_PREPARED, STATE_PAUSED -> {
        startPlayer()
      }
    }
  }

  private fun preparePlayer() {
    mediaPlayer.setDataSource(url)

    mediaPlayer.setOnErrorListener { _, what, extra ->
      Log.e("setOnErrorListener", "$what $extra")
      false
    }

    mediaPlayer.setOnPreparedListener {
      Log.d("setOnPreparedListener", "setOnPreparedListener")
      play.isEnabled = true
      playerState = STATE_PREPARED
    }

    mediaPlayer.prepareAsync()

    mediaPlayer.setOnCompletionListener {
      Log.d("setOnCompletionListener", "setOnCompletionListener")
      play.text = "PLAY"
      playerState = STATE_PREPARED
    }
  }

  private fun startPlayer() {
    mediaPlayer.start()
    play.text = "PAUSE"
    playerState = STATE_PLAYING
  }

  private fun pausePlayer() {
    mediaPlayer.pause()
    play.text = "PLAY"
    playerState = STATE_PAUSED
  }
}