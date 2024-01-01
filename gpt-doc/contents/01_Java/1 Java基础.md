
多线程
1、java中有哪些方式实现多线程？
（1）继承 Thread 类，即实现线程的 run() 方法，start()方法启动线程；
（2）实现 Runnable 接口，即任务，交给 Thread 去运行；
（3）用Timer，Timer里跑TimerTask；