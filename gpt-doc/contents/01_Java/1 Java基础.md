
## 多线程
### 1、java中有哪些方式实现多线程？
- 继承 Thread 类，即实现线程的 run() 方法，start() 方法启动线程；
- 实现 Runnable 接口，即任务，交给 Thread 去运行；
- 实现 Callable 接口，交给 FutureTask（用 FutureTask 的 get 可以拿到 call 的返回值），交给 Thread 去运行。FutureTask 本质也是 Runnable；
- 线程池 ThreadPool，核心工具类 Executors
  1. newFixedThreadPool
  2. newSingleThreadScheduledExecutor
  3. newCachedThreadPool
  4. newScheduledThreadPool
  5. newWorkStealingPool
- 用 Timer，Timer 里跑 TimerTask；