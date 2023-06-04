package com.example

// возможные варианты:
// 01
// 10
// 11
// 00 - этот вариант возможен только без @Volatile


//@Volatile
var a = 0
//@Volatile
var b = 0

fun main() {

    val t1 = Thread {
      b = 1
      print("$a")
    }

    val t2 = Thread {
      a = 1
      print("$b")
    }

    t1.start()
    t2.start()
    t1.join()
    t2.join()
}
