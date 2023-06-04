package com.example

// для правильной работы нужно синхронизовать обращение к x
// или использовать AtomicInteger

//val x = AtomicInteger(0)
var x = 0

fun main() {
  //val lock = Object()

  val t1 = Thread {
    repeat(10000) {
      //x.incrementAndGet()
      //synchronized((lock)) {
      x++
      //}
    }
  }

  val t2 = Thread {
    repeat(10000) {
      //x.decrementAndGet()
      //synchronized((lock)) {
      x--
      //}
    }
  }

  t1.start()
  t2.start()

  t1.join()
  t2.join()

  println("x: $x")
}