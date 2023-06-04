package com.example;

// Однопоточная версия
class Foo {
  private Object helper = null;
  public Object getHelper() {
    if (helper == null)
      helper = new Object();
    return helper;
  }
}


// Правильная, но "дорогая" по времени выполнения многопоточная версия
class Foo2 {
  private Object helper = null;
  public synchronized Object getHelper() {
    if (helper == null)
      helper = new Object();
    return helper;
  }
}

// Некорректно работающая (в Symantec JIT и версиях Java 1.4 и ранее) многопоточная версия
// Шаблон "Double-Checked Locking"
class Foo3 {
  private Object helper = null;
  public Object getHelper() {
    if (helper == null) {
      synchronized(this) {
        if (helper == null) {
          helper = new Object();
        }
      }
    }
    return helper;
  }
}

// Работает с новой семантикой volatile
// Не работает в Java 1.4 и более ранних версиях из-за семантики volatile
class Foo4 {
  private volatile Object helper = null;
  public Object getHelper() {
    if (helper == null) {
      synchronized(this) {
        if (helper == null)
          helper = new Object();
      }
    }
    return helper;
  }
}