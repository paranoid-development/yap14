## Yandex Practicum
# Multithreading

---

### Процессы

![](images/processes.jpg)


```agsl
<activity
    ...
    android:process="my.process1"
/>

<activity
    ...
    android:process="my.process2"
/>
```

---

### Потоки

![](images/multithread.png)



![](images/thread.png)

---

```java
//java

public void synchronized myFun() {
...
}

private Object lock = new Object();

public void myFun2() {
    synchronized(lock) {
    }
}

public static synchronized myFun3() {
}
```

```kotlin
//kotlin
val lock = Object()
@Synchronized
fun myFun() {
}

fun myFun2() {
  synchronized(lock) {
  }
}

companion object {
  @Synchronized
  fun myFun3() {
  }
}


```
![](images/thread_lifecycle_java.jpeg)
```
List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());

Map<Integer, String> syncMap = Collections.synchronizedMap(new HashMap<>());

ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

BlockingQueue

AtomicInteger atomicInt = new AtomicInteger(0);

ExecutorService executor = Executors.newFixedThreadPool(2);

```
