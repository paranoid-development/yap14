package com.example;

public class HappensBefore2 {
  int x = 0;
  boolean done = false;

  public void thread1() {
    x = 1;
    done = true;
  }

  public void thread2() {
    while (!done) {
      //...
    }
    System.out.println(x);
  }
}
