package com.gpengtao.java.thread2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by gpengtao on 14-10-15.
 */
public class TestFutureCancel {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Object> future = executor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(1000 * 5);
                return "finish";
            }
        });

        try {
            future.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            boolean result = future.cancel(true);
            System.out.println("result result:" + result);
            System.out.println("future cancel status:" + future.isCancelled());
            System.out.println("future done status:" + future.isDone());
        }

        try {
            Object o = future.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
