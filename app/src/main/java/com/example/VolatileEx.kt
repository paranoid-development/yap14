package com.example

// @Volatile
var flag = false

fun main() {
  println("starting...")

  Thread {
    while (!flag) {
      //...
    }
    println("thread got flag TRUE")
  }.start()

  Thread.sleep(1000)

  flag = true
  println("flag is true now")
}