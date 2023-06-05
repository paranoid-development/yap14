package com.example.ya

import android.app.Application
import android.net.TrafficStats
import android.os.StrictMode

class App : Application() {
  override fun onCreate() {
    super.onCreate()
    //initStrictMode()
  }

  private fun initStrictMode() {
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(
        StrictMode.ThreadPolicy.Builder()
          .detectAll()
          .penaltyLog()
          .build(),
      )
    }
  }
}