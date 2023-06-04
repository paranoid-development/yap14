package com.example.ya

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  private lateinit var button: Button
  private lateinit var textView: TextView
  private var countDownTimer: MyCountDownTimer? = null
  private lateinit var handler: Handler
  private var isTimerRunning: Boolean = false
  private var remainingTime: Long = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    button = findViewById(R.id.button)
    textView = findViewById(R.id.myTextView)
    handler = Handler(Looper.getMainLooper())

    button.setOnClickListener {
      if (isTimerRunning) {
        pauseTimer()
      } else {
        startTimer()
      }
    }
  }

  private fun startTimer() {
    Log.d("XXX", "startTimer $remainingTime $this")
    countDownTimer = object : MyCountDownTimer(30000 - remainingTime, 1000) {
      override fun onTick(millisUntilFinished: Long) {
        val seconds = millisUntilFinished / 1000
        textView.text = "Осталось времени: $seconds секунд"
        remainingTime = millisUntilFinished
        Log.d("XXX", "onTick $remainingTime $this")
      }

      override fun onFinish() {
        //Log.d("XXX2", "$remainingTime")
        handler.post {
          textView.text = "Таймер завершен!"
        }
      }
    }
    countDownTimer?.start()
    isTimerRunning = true
  }

  private fun pauseTimer() {
    countDownTimer?.cancel()
    isTimerRunning = false
    handler.post {
      textView.text = "Таймер остановлен!"
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    if (isTimerRunning) {
      countDownTimer?.cancel()
    }
  }
}