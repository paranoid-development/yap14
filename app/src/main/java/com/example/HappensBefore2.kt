package com.example

var xx = 0
var done = false

fun main() {

  val t1 = Thread {
    xx = 1
    done = true
  }

  val t2 = Thread {
    while (!done) {
      //...
    }
    println(xx)
  }

  t1.start()
  t2.start()
  t1.join()
  t2.join()
}