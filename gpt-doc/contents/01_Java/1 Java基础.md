
## 多线程
### 1、java中有哪些方式实现多线程？
- 继承 Thread 类，即实现线程的 run() 方法，start()方法启动线程；
- 实现 Runnable 接口，即任务，交给 Thread 去运行；
- 实现 FutureTask，里面指定了 Callable，用get可以拿到call的返回值，交给 Thread 去运行。FutureTask 本质也是 Runnable；
- 用Timer，Timer里跑TimerTask；