package com.kolnick.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caochaojie
 * 2018/11/15 16:40
 * @description
 */
public class Worker {

    public final static Worker instance = new Worker();
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private Lock shutDownLock = new ReentrantLock();
    private Condition shutDownCondition = shutDownLock.newCondition();

    private Worker() {
    }

    public static Worker getInstance() {
        return instance;
    }


    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Lock getShutDownLock() {
        return shutDownLock;
    }

    public void setShutDownLock(Lock shutDownLock) {
        this.shutDownLock = shutDownLock;
    }

    public Condition getShutDownCondition() {
        return shutDownCondition;
    }

    public void setShutDownCondition(Condition shutDownCondition) {
        this.shutDownCondition = shutDownCondition;
    }
}
