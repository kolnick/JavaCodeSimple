package com.caochaojie.concurrent.thread.aqs.reetrantlock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caochaojie
 * 2022/12/6
 * @description
 */
public class ReentrantLockTest {

    @Test
    public void lock() {
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            reentrantLock.lock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
